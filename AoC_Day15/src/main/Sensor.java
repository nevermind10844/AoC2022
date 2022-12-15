package main;

public class Sensor extends TwoDPlaceable {
	private Beacon beacon;

	public Sensor(int x, int y) {
		super(x, y);
		this.beacon = null;
	}

	public void setBeacon(Beacon b) {
		this.beacon = b;
	}

	public Beacon getBeacon() {
		return this.beacon;
	}
}
