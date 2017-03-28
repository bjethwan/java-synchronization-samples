package com.bjethwan;

import java.util.concurrent.TimeUnit;

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	private static int SLEEP_GRANULARITY = 5;

	public SleepyBoundedBuffer(int capacity) {
		super(capacity);
	}

	public void put(V v) throws InterruptedException{
		
		while(true){
			synchronized (this){
				if(!isFull()){
					doPut(v);
					return;
				}
			}
			TimeUnit.SECONDS.sleep(SLEEP_GRANULARITY);
		}
	}

	public V get() throws InterruptedException{
		
		while(true){
			synchronized (this) {
				if(!isEmpty()){
					return doTake();
				}
			}
			TimeUnit.SECONDS.sleep(SLEEP_GRANULARITY);
		}
	}
	
}

