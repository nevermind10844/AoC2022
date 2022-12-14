package main;

import java.util.Objects;

public class Tile {
	private int x;
	private int y;
	private TileType tileType;

	public Tile(int x, int y) {
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

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	
	@Override
	public String toString() {
		return "Tile [x=" + x + ", y=" + y + ", tileType=" + tileType + "]";
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
		Tile other = (Tile) obj;
		return x == other.x && y == other.y;
	}

	public enum TileType {
		ROCK, AIR, SAND, SPAWN;
	}
}
