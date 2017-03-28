package com.bjethwan;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V>{

	public BoundedBuffer(int capacity) {
		super(capacity);
	}
	
	//BLOCKS-UNTIL: not-full
	public synchronized void put(V v) throws InterruptedException{
		while(isFull())
			wait();
		doPut(v);
		notifyAll();
	}
	
	//BLOCKS-UNTIL: not-empty
	public synchronized V get() throws InterruptedException{
		while(isEmpty())
			wait();
		V v = doTake();
		notifyAll();
		return v;
	}
	
	

}
