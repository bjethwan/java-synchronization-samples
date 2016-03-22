package avoid.using.reentrant.locks;

import java.util.concurrent.TimeUnit;

public abstract class  Teller {

	public static long getFixedDelayComponentNanos(long timeout, TimeUnit unit){
		return timeout/7;
	}


	public static long getRandomDelayModulusNanos(long timeout, TimeUnit unit){
		return 10;
	}

	public abstract boolean transferMoney(Account fromAcct, Account toAcct, DollarAmount amount, long timeout, TimeUnit unit) 
					throws InsufficientFundsException, InterruptedException;
}
