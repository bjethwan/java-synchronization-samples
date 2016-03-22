package avoid.using.reentrant.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	public Lock lock = new ReentrantLock();

	DollarAmount amount;

	
	public Account(DollarAmount amount){
		this.amount = new DollarAmount(amount);	
	}
	
	public Account(int amount){
		this.amount = new DollarAmount(amount);	
	}

	public void credit(DollarAmount amt){
		amount.add(amt);
	}

	public void debit(DollarAmount amt){
		amount.substract(amt);
	}

	public DollarAmount getBalance(){
		return amount;

	}

}
