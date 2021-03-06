package com.gnatyuk.first;

public class FirstTestClass {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Thread thread = new Thread(new ImplementRunnable());
	thread.start();
	thread.setPriority(Thread.NORM_PRIORITY);
	
	Thread thread1 = new Thread(new ImplementRunnable());
	thread1.start();
	thread1.setPriority(Thread.MIN_PRIORITY);
	
	Thread thread2 = new Thread(new ImplementRunnable());
	thread2.start();
	thread2.setPriority(Thread.MAX_PRIORITY);
	
	while(thread.isAlive() || thread1.isAlive() || thread2.isAlive());
	
	System.out.println("Goodbay:)");
    }
}

class ImplementRunnable implements Runnable{
    public void run(){
	for (int i = 0; i < 5; i++) {
	    System.out.println(i + "name of the thread" + Thread.currentThread());
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		System.out.println("Main thread stoped");
		e.printStackTrace();
	    }
	}
    }
}