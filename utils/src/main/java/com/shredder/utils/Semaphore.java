package com.shredder.utils;

public class Semaphore {

	// init as true to allow the first caller of release to have it
	private boolean signal = true;

	public synchronized void take() {
		this.signal = true;
		this.notify();
	}

	public synchronized void release() throws InterruptedException {
		while (!this.signal)
			wait();
		this.signal = false;
	}

}