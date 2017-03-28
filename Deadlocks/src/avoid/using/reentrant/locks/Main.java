package avoid.using.reentrant.locks;

import java.util.concurrent.TimeUnit;

/**
 ** Possible outcome 1 ** 
 ------------------------
 
 Bipin's Account Balance: 1500
 Neha's  Account Balance: 5000

-- Transaction Details --
 Bipin --> Neha  : $1000
 Neha  --> Bipin : $500


 Bipin's Account Balance: 1000
 Neha's  Account Balance: 5500

 */


/**
 ** Possible outcome 2 ** 
 ------------------------
 
 Bipin's Account Balance: 1500
 Neha's  Account Balance: 5000

-- Transaction Details --
 Bipin --> Neha  : $1000
 Neha  --> Bipin : $500


 Failed!!!
 Bipin's Account Balance: 2000
 Neha's  Account Balance: 4500

 */

/**
 ** Possible outcome 3 ** 
 ------------------------
 
 Bipin's Account Balance: 1500
 Neha's  Account Balance: 5000

-- Transaction Details --
 Bipin --> Neha  : $1000
 Neha  --> Bipin : $500


 Failed!!!
 Bipin's Account Balance: 500
 Neha's  Account Balance: 6000

 */

public class Main {




	public static void main(String[] args) throws Exception {
		test3();
	}
	
	public static void test1() throws InterruptedException{
		
		InteligentTimeBudgetTeller teller1 = new InteligentTimeBudgetTeller();
		InteligentTimeBudgetTeller teller2 = new InteligentTimeBudgetTeller();

		Account bipinAcct = new Account(1500);
		Account nehaAcct  = new Account(5000);

		System.out.println("Bipin's Account Balance: " + bipinAcct.getBalance() );
		System.out.println("Neha's  Account Balance: " + nehaAcct.getBalance() );
		System.out.println();

		System.out.println("-- Transaction Details --");
		System.out.println("Bipin --> Neha : $1000");
		System.out.println("Neha --> Bipin : $500");
		System.out.println();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					teller1.transferMoney(bipinAcct, nehaAcct, new DollarAmount(1000), 3, TimeUnit.SECONDS);
				}catch(Exception e){
					System.out.println(e);
				}


			}});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					teller2.transferMoney(nehaAcct, bipinAcct, new DollarAmount(500), 3, TimeUnit.SECONDS);
				}catch(Exception e){
					System.out.println(e);
				}
			}});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		
		System.out.println("Bipin's Account Balance: " + bipinAcct.getBalance() );
		System.out.println("Neha's  Account Balance: " + nehaAcct.getBalance() );
	}
	
	public static void test2() throws InterruptedException
	{

		ClumsyTeller teller1 = new ClumsyTeller();
		ClumsyTeller teller2 = new ClumsyTeller();

		Account bipinAcct = new Account(1500);
		Account nehaAcct  = new Account(5000);
		
		System.out.println("Bipin's Account Balance: " + bipinAcct.getBalance() );
		System.out.println("Neha's  Account Balance: " + nehaAcct.getBalance() );
		System.out.println();

		System.out.println("-- Transaction Details --");
		System.out.println("Bipin --> Neha : $1000");
		System.out.println("Neha --> Bipin : $500");
		System.out.println();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					teller1.transferMoney(bipinAcct, nehaAcct, new DollarAmount(1000));
				}catch(Exception e){
					System.out.println(e);
				}


			}});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					teller2.transferMoney(nehaAcct, bipinAcct, new DollarAmount(500));
				}catch(Exception e){
					System.out.println(e);
				}
			}});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		
		System.out.println("Bipin's Account Balance: " + bipinAcct.getBalance() );
		System.out.println("Neha's  Account Balance: " + nehaAcct.getBalance() );
	}
	
	public static void test3() throws InterruptedException
	{

		NiceLockOrderingTeller teller1 = new NiceLockOrderingTeller();
		NiceLockOrderingTeller teller2 = new NiceLockOrderingTeller();

		Account bipinAcct = new Account(1500);
		Account nehaAcct  = new Account(5000);
		
		System.out.println("Bipin's Account Balance: " + bipinAcct.getBalance() );
		System.out.println("Neha's  Account Balance: " + nehaAcct.getBalance() );
		System.out.println();

		System.out.println("-- Transaction Details --");
		System.out.println("Bipin --> Neha : $1000");
		System.out.println("Neha --> Bipin : $500");
		System.out.println();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					teller1.transferMoney(bipinAcct, nehaAcct, new DollarAmount(1000));
				}catch(Exception e){
					System.out.println(e);
				}


			}});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try{
					teller2.transferMoney(nehaAcct, bipinAcct, new DollarAmount(500));
				}catch(Exception e){
					System.out.println(e);
				}
			}});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		
		System.out.println("Bipin's Account Balance: " + bipinAcct.getBalance() );
		System.out.println("Neha's  Account Balance: " + nehaAcct.getBalance() );
	}


}
