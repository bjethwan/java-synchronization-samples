package com.bjethwan;

import com.bjethwan.exceptions.BufferEmptyExeption;
import com.bjethwan.exceptions.BufferFullException;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	public GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v) throws BufferFullException{
		if(isFull()){
			throw new BufferFullException();
		}
		doPut(v);
	}
	
	public synchronized V get() throws BufferEmptyExeption{
		if(isEmpty()){
			throw new BufferEmptyExeption();
		}
		return doTake();
	}

}

