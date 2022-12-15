package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import main.Tile.TileType;

public class Map {
	private List<Tile> tileList;

	private boolean endOfGame;

	public Map() {
		this.tileList = new ArrayList<>();
		this.endOfGame = false;
	}

	public boolean getEndOfGame() {
		return this.endOfGame;
	}

	public int getMinX() {
		return this.tileList.stream().mapToInt(Tile::getX).min().getAsInt();
	}

	public int getMaxX() {
		return this.tileList.stream().mapToInt(Tile::getX).max().getAsInt();
	}

	public int getMinY() {
		return this.tileList.stream().mapToInt(Tile::getY).min().getAsInt();
	}

	public int getMaxY() {
		return this.tileList.stream().mapToInt(Tile::getY).max().getAsInt();
	}

	public void addTile(Tile tile) throws UnsupportedOperationException {
		if (tileList.contains(tile))
			throw new UnsupportedOperationException(
					String.format("you cannot add the same tile address twice! %d, %d", tile.getX(), tile.getY()));
		this.tileList.add(tile);
	}

	public Tile getTile(int x, int y) {
		Optional<Tile> optTile = this.tileList.stream().filter(tile -> tile.equals(new Tile(x, y))).findFirst();
		if (!optTile.isPresent())
			return null;
		return optTile.get();
	}

	public void thinAir() {
		int maxX = this.tileList.stream().mapToInt(Tile::getX).max().getAsInt();
		int minX = this.tileList.stream().mapToInt(Tile::getX).min().getAsInt();
		int maxY = this.tileList.stream().mapToInt(Tile::getY).max().getAsInt();
		int minY = this.tileList.stream().mapToInt(Tile::getY).min().getAsInt();

		for (int y = minY; y < maxY + 1; y++) {
			for (int x = minX; x < maxX + 1; x++) {
				Tile t = this.getTile(x, y);
				if (t == null) {
					t = new Tile(x, y);
					this.addTile(t);
				}
			}
		}
	}

	private int getManhattenDistance(Tile a, Tile b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}

	public void spreadSensors() {
		List<Sensor> sensorList = this.tileList.stream().filter(tile -> tile.getTileType() == TileType.SENSOR)
				.map(tile -> (Sensor) tile.getOccupant()).toList();
		for (Sensor sensor : sensorList) {
			int manhattenDistance = this.getManhattenDistance(this.getTile(sensor.getX(), sensor.getY()),
					this.getTile(sensor.getBeacon().getX(), sensor.getBeacon().getY()));
			for (int y = 0; y <= manhattenDistance; y++) {
				for (int x = 0; x <= manhattenDistance - y; x++) {
					Tile topRight = this.getTile(sensor.getX() + x, sensor.getY() - y);
					Tile topLeft = this.getTile(sensor.getX() - x, sensor.getY() - y);
					Tile botRight = this.getTile(sensor.getX() + x, sensor.getY() + y);
					Tile botLeft = this.getTile(sensor.getX() - x, sensor.getY() + y);

					if (topRight != null)
						topRight.setScanned(true);
					if (topLeft != null)
						topLeft.setScanned(true);
					if (botRight != null)
						botRight.setScanned(true);
					if (botLeft != null)
						botLeft.setScanned(true);
					System.out.println(String.format("Doing stuff for %s...", sensor));
				}
			}
		}
	}

	public long getScannedCountInLine(int y) {
		return this.tileList.stream()
				.filter(tile -> tile.getY() == y && tile.isScanned() && tile.getTileType() != TileType.BEACON).count();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		int maxX = this.tileList.stream().mapToInt(Tile::getX).max().getAsInt();
		int minX = this.tileList.stream().mapToInt(Tile::getX).min().getAsInt();
		int maxY = this.tileList.stream().mapToInt(Tile::getY).max().getAsInt();
		int minY = this.tileList.stream().mapToInt(Tile::getY).min().getAsInt();

		for (int y = minY; y < maxY + 1; y++) {
			for (int x = minX; x < maxX + 1; x++) {
				try {
					Tile t = this.getTile(x, y);
					if (t.getTileType() == TileType.BEACON)
						sb.append(t.isScanned() ? "b" : "B");
					if (t.getTileType() == TileType.AIR)
						sb.append(t.isScanned() ? "#" : ".");
					if (t.getTileType() == TileType.SENSOR)
						sb.append(t.isScanned() ? "s" : "S");
				} catch (NoSuchElementException ex) {
					ex.printStackTrace();
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
