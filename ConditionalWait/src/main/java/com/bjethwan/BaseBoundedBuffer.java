package com.bjethwan;

@SuppressWarnings("unchecked")

public abstract class BaseBoundedBuffer<V> {
	
	private V[] buff;
	private int head;
	private int tail;
	private int count;

	protected BaseBoundedBuffer(int capacity){
		buff = (V[])new Object[capacity];
	}

	protected synchronized final void doPut(V v){
		buff[tail++] = v;
		if(tail == buff.length){
			tail = 0;
		}
		++count;
	}

	protected synchronized final V doTake(){
		V v = buff[head];
		buff[head]=null;
		if(++head == buff.length){
			head = 0;
		}
		--count;
		return v;
	}

	public boolean isEmpty(){
		return count == 0;
	}

	public boolean isFull(){
		return count == buff.length;
	}
}

