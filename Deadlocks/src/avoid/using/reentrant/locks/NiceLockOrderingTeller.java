package avoid.using.reentrant.locks;

import java.util.concurrent.TimeUnit;

public class NiceLockOrderingTeller 
{
	private static final Object tieLock = new Object();

	public void transferMoney(Account fromAcct, Account toAcct, DollarAmount amount)
			throws InsufficientFundsException, InterruptedException {

		class Helper {
			public void transfer() throws InsufficientFundsException{
				if(fromAcct.getBalance().compareTo(amount)<0)	throw new InsufficientFundsException();
				else{
					fromAcct.debit(amount);
					toAcct.credit(amount);
				}
			}
		}
		int fromHash = System.identityHashCode(fromAcct), toHash = System.identityHashCode(toAcct);

		if(fromHash < toHash){
			synchronized (fromAcct) {
				synchronized (toAcct) {
					new Helper().transfer();
				}
			}
		}else if(fromHash > toHash){
			synchronized (toAcct) {
				synchronized (fromAcct) {
					new Helper().transfer();
				}
			}
		}else{
			//system level lock for such cases - 
			//since identityHashCode is memory address based, it's quite possible.
			synchronized (tieLock) {					
				synchronized (fromAcct) {
					synchronized (toAcct) {
						new Helper().transfer();
					}
				}
			}
		}
	} //end of transferMoney method
}  // end of class
