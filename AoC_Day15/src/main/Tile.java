package main;

public class Tile extends TwoDPlaceable{
	private TileType tileType;
	private TwoDPlaceable occupant;
	private boolean scanned;

	public Tile(int x, int y) {
		super(x, y);
		this.tileType = TileType.AIR;
		this.scanned = false;
	}
	
	public Tile(TwoDPlaceable occupant) {
		super(occupant.getX(), occupant.getY());
		this.occupant = occupant;
		if(occupant instanceof Sensor)
			this.tileType = TileType.SENSOR;
		else if(occupant instanceof Beacon) 
			this.tileType = TileType.BEACON;
		else
			this.tileType = TileType.AIR;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	
	public TwoDPlaceable getOccupant() {
		return this.occupant;
	}
	
	public boolean isScanned() {
		return scanned;
	}

	public void setScanned(boolean scanned) {
		this.scanned = scanned;
	}



	public enum TileType {
		AIR, BEACON, SENSOR;
	}
}
