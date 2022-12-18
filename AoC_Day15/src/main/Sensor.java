package main;

public class Sensor extends TwoDPlaceable {
	private Beacon beacon;
	private int manhattenDistance;

	public Sensor(int x, int y) {
		super(x, y);
		this.beacon = null;
	}

	public void setBeacon(Beacon b) {
		this.beacon = b;
		this.manhattenDistance = this.getManhattenDistance(this.beacon);
	}

	public Beacon getBeacon() {
		return this.beacon;
	}

	public int getPreCalculatedManhattenDistance() {
		return this.manhattenDistance;
	}

	public int getXDistance(int x) {
		return x - this.x;
	}

	public int getYDistance(int y) {
		return y - this.y;
	}
}
