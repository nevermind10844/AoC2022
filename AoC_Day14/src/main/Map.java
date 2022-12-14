package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import main.Tile.TileType;

public class Map {
	public List<Tile> tileList;

	private boolean endOfGame;
	private boolean stillMoving;
	private int currentX;
	private int currentY;
	
	private Tile spawn;

	public Map() {
		this.tileList = new ArrayList<>();
		this.endOfGame = false;
		this.stillMoving = false;
	}
	
	public void setSpawn (Tile spawn){
		this.spawn = spawn;
		this.addTile(spawn);
	}

	public boolean getEndOfGame() {
		return this.endOfGame;
	}

	public void addTile(Tile tile) throws UnsupportedOperationException {
		if (tileList.contains(tile))
			throw new UnsupportedOperationException("you cannot add the same tile address twice!");
		this.tileList.add(tile);
	}

	public Tile getTile(int x, int y) {
		Optional<Tile> optTile = this.tileList.stream().filter(tile -> tile.equals(new Tile(x, y))).findFirst();
		if (!optTile.isPresent())
			return null;
		return optTile.get();
	}

	public void invalidate() throws Exception {
		if (this.stillMoving) {
			Tile currentTile = this.getTile(this.currentX, this.currentY);
			if (currentTile == null)
				throw new Exception("something weird happend: could find current tile");
			Tile bottomTile = this.getTile(currentTile.getX(), currentTile.getY() + 1);
			if (bottomTile == null) {
				currentTile.setTileType(TileType.AIR);
				this.stillMoving = false;
				this.endOfGame = true;
			} else {
				if(bottomTile.getTileType() == TileType.ROCK || bottomTile.getTileType() == TileType.SAND) {
					Tile leftTile = this.getTile(currentTile.getX()-1, currentTile.getY()+1);
					if(leftTile == null) {
						currentTile.setTileType(TileType.AIR);
						this.stillMoving = false;
						this.endOfGame = true;
					} else {
						if(leftTile.getTileType() == TileType.ROCK || leftTile.getTileType() == TileType.SAND) {
							Tile rightTile = this.getTile(currentTile.getX()+1, currentTile.getY()+1);
							if(rightTile == null) {
								currentTile.setTileType(TileType.AIR);
								this.stillMoving = false;
								this.endOfGame = true;
							} else {
								if(rightTile.getTileType() == TileType.ROCK || rightTile.getTileType() == TileType.SAND) {
									this.stillMoving = false;
									System.out.println(this);
								} else {
									currentTile.setTileType(TileType.AIR);
									rightTile.setTileType(TileType.SAND);
									this.currentX = rightTile.getX();
									this.currentY = rightTile.getY();
								}
							}
						} else {
							currentTile.setTileType(TileType.AIR);
							leftTile.setTileType(TileType.SAND);
							this.currentX = leftTile.getX();
							this.currentY = leftTile.getY();
						}
					}
				} else {
					currentTile.setTileType(TileType.AIR);
					bottomTile.setTileType(TileType.SAND);
					this.currentX = bottomTile.getX();
					this.currentY = bottomTile.getY();
				}
			}
		} else {
			this.spawn.setTileType(TileType.SAND);
			this.currentX = this.spawn.getX();
			this.currentY = this.spawn.getY();
			this.stillMoving = true;
		}
	}
	
	public void thinAir() {
		int maxX = this.tileList.stream().mapToInt(Tile::getX).max().getAsInt();
		int minX = this.tileList.stream().mapToInt(Tile::getX).min().getAsInt();
		int maxY = this.tileList.stream().mapToInt(Tile::getY).max().getAsInt();
		int minY = this.tileList.stream().mapToInt(Tile::getY).min().getAsInt();

		for (int y = minY; y < maxY + 1; y++) {
			for (int x = minX; x < maxX + 1; x++) {
				Tile t = this.getTile(x, y);
				if(t == null) {
					t = new Tile(x, y);
					t.setTileType(TileType.AIR);
					this.addTile(t);
				}
			}
		}
	}
	
	public int getSandBlockCount() {
		return this.tileList.stream().filter(tile -> tile.getTileType() == TileType.SAND).toList().size();
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
					sb.append(t.getTileType() == TileType.ROCK ? "#" : (t.getTileType() == TileType.AIR ? "." : "O"));
				} catch (NoSuchElementException ex) {
					ex.printStackTrace();
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
