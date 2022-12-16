package main;

import java.util.Objects;

public class TwoDPlaceable {
	private int x;
	private int y;

	public TwoDPlaceable(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getManhattenDistance(TwoDPlaceable other) {
		return Math.abs(other.x - this.x) + Math.abs(other.y - this.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwoDPlaceable other = (TwoDPlaceable) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "TwoDPlaceable [x=" + x + ", y=" + y + "]";
	}

}
