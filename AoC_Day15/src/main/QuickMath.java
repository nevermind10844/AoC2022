package main;

import java.util.List;

public class QuickMath implements Runnable {

	private int currentY;
	private int size;

	private int xResult;
	private int yResult;

	private boolean result;

	private List<Sensor> sensorList;

	public QuickMath(int currentY, int size, List<Sensor> sensorList) {
		this.currentY = currentY;
		this.size = size;
		this.sensorList = sensorList;
		this.result = false;
	}

	public int getxResult() {
		return xResult;
	}

	public int getyResult() {
		return yResult;
	}

	public boolean hasResult() {
		return this.result;
	}

	@Override
	public void run() {
		for (int x = 0; x <= this.size; x++) {
			boolean scanned = false;
			for (Sensor sensor : sensorList) {
				int manhattenDistance = sensor.getManhattenDistance(x, this.currentY);
				if (manhattenDistance <= sensor.getPreCalculatedManhattenDistance()) {
					scanned = true;
					int xDistance = sensor.getXDistance(x);
					int yDistance = sensor.getYDistance(this.currentY);
					if (xDistance < 0)
						x = sensor.getX() + (manhattenDistance-yDistance);
					break;
				}
			}
			if (!scanned) {
				this.xResult = x;
				this.yResult = this.currentY;
				System.err.println("result: " + this.xResult + "::" + this.yResult);
				break;
			}
		}
	}
}
