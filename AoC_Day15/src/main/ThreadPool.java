package main;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.Size2DSyntax;

public class ThreadPool implements Runnable{
	private List<Thread> threadList;
	private boolean full;
	private boolean stop;
	
	public ThreadPool() {
		this.threadList = new ArrayList<>();
	}
	
	public boolean isFull() {
		System.out.println("thread pool is full ("+this.threadList.size()+")");
		return this.full;
	}
	
	public void stop() {
		this.stop = true;
	}
	
	public void addRunnableAndStartThread(Runnable r) {
		Thread t = new Thread(r);
		t.start();
		threadList.add(t);
		System.out.println("thread added ("+this.threadList.size()+")");
		if(this.threadList.size()>=4)
			this.full = true;
	}

	@Override
	public void run() {
		while(!stop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (Thread thread : this.threadList) {
				if(!thread.isAlive()) {
					this.threadList.remove(thread);
					System.out.println("thread removed ("+this.threadList.size()+")");
					this.full = false;
					break;
				}
			}
		}
	}
}
