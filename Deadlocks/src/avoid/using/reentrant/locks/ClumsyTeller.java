package avoid.using.reentrant.locks;

import java.util.concurrent.TimeUnit;

public class ClumsyTeller{

	public boolean transferMoney(Account fromAcct, Account toAcct, DollarAmount amount)
			throws InsufficientFundsException, InterruptedException 
	{
		synchronized (fromAcct) {
			TimeUnit.SECONDS.sleep(3);
			synchronized (toAcct) {
				if(fromAcct.getBalance().compareTo(amount)<0)
					throw new InsufficientFundsException();
				else{
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}

			}
		}
		return true;
	}

}
