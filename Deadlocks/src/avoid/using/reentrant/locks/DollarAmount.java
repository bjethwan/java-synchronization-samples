package avoid.using.reentrant.locks;

public class DollarAmount implements Comparable<DollarAmount>{
	private int amount;
	
	public DollarAmount(int amt){
		this.amount = amt;
	}
	
	public DollarAmount(DollarAmount that){
		this.amount = that.amount;
	}
	
	public boolean add(DollarAmount amt){
		this.amount = this.amount+ amt.amount;
		return true;
	}
	
	public boolean substract(DollarAmount amt){
		this.amount = this.amount - amt.amount;
		return true;
	}

	@Override
	public int compareTo(DollarAmount that) {
		if(this.amount == that.amount){
			return 0;
		}else if(this.amount < that.amount){
			return -1;
		}else{
			return 1;
		}
	}

	@Override
	public String toString() {
		return "" + amount;
	}
}
