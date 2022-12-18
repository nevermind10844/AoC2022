package main;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool implements Runnable{
	private List<Thread> threadList;
	private boolean full;
	private boolean stop;
	
	public ThreadPool() {
		this.threadList = new ArrayList<>();
	}
	
	public boolean isFull() {
		return this.full;
	}
	
	public void stop() {
		this.stop = true;
	}
	
	public void addRunnableAndStartThread(Runnable r) {
		Thread t = new Thread(r);
		t.start();
		threadList.add(t);
		if(this.threadList.size()>=4)
			this.full = true;
	}

	@Override
	public void run() {
		while(!stop) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i=threadList.size()-1; i>=0; i--) {
				Thread t = threadList.get(i);
				if(!t.isAlive()) {
					this.threadList.remove(i);
					this.full = false;
					break;
				}
			}
		}
	}
}
