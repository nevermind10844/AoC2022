package main;

import java.util.List;

public class QuickMath implements Runnable{

	private int xStart;
	private int xEnd;
	private int yStart;
	private int yEnd;
	
	private int xResult;
	private int yResult;
	
	private boolean running;
	
	private List<Sensor> sensorList;
	
	public QuickMath(int xStart, int yStart, int length, List<Sensor> sensorList) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xStart + length;
		this.yEnd = yStart + length;
		this.sensorList = sensorList;
	}

	public int getxStart() {
		return xStart;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}

	public int getxEnd() {
		return xEnd;
	}

	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}

	public int getyStart() {
		return yStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}

	public int getyEnd() {
		return yEnd;
	}

	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}

	public int getxResult() {
		return xResult;
	}

	public int getyResult() {
		return yResult;
	}

	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {
		for (int x = this.xStart; x <= this.xEnd; x++) {
//			System.out.println(this.xStart + ": " + (x - this.xStart) / (this.xEnd-this.xStart) + "%");
			for (int y = this.yStart; y <= this.yEnd; y++) {
				Tile t = new Tile(x, y);
//				System.out.println(t);
				boolean scanned = false;
				for (Sensor sensor : sensorList) {
//				System.out.println("checking sensor " + sensor + " for tile " + t);
					int manhattenDistance = sensor.getManhattenDistance(sensor.getBeacon());
					if (sensor.getManhattenDistance(t) <= manhattenDistance)
						scanned = true;
				}
				if (!scanned) {
					this.xResult = x;
					this.yResult = y;
					System.out.println("result: " + this.xResult + "::" + this.yResult);
					x = 4000000 + 1;
					y = 4000000 + 1;
					this.running = false;
 				}
			}
		}
	}
}
