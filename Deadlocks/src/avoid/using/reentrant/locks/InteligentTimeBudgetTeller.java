package avoid.using.reentrant.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InteligentTimeBudgetTeller{
	
	public boolean transferMoney(Account fromAcct, Account toAcct, DollarAmount amount, long timeout, TimeUnit unit) 
			throws InsufficientFundsException, InterruptedException{
		
		Random rnd       =  new Random(); 
		long fixedDelay  =  getFixedDelayComponentNanos(timeout, unit);
		long randomMod   =  getRandomDelayModulusNanos(timeout, unit);
		long stopTime    =  System.nanoTime()+ unit.toNanos(timeout);

		while(true){
			if(fromAcct.lock.tryLock()){
				try{
					if(toAcct.lock.tryLock()){
						try{
							if(fromAcct.getBalance().compareTo(amount) < 0){
								throw new InsufficientFundsException();
							}else{
								fromAcct.debit(amount);
								toAcct.credit(amount);
								return true;
							}
						}finally{
							toAcct.lock.unlock();
						}
					}
				}finally{
					fromAcct.lock.unlock();
				}
			}
			if(System.nanoTime() > stopTime){
				return false;
			}
			TimeUnit.NANOSECONDS.sleep(fixedDelay + rnd.nextLong()%randomMod);
			
		} //end of while loop
	}

	public static long getFixedDelayComponentNanos(long timeout, TimeUnit unit){
		return timeout/7;
	}


	public static long getRandomDelayModulusNanos(long timeout, TimeUnit unit){
		return 10;
	}
	
}


