import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
	private List<Tile> tiles;
	private Knot head;

	public Board(Knot head) {
		this.tiles = new ArrayList<>();
		this.head = head;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}

	public Knot getKnot() {
		return head;
	}

	public void setHead(Knot head) {
		this.head = head;
	}

	public Tile addTile(Tile tile) {
		if (this.tiles.contains(tile))
			return this.tiles.get(this.tiles.indexOf(tile));
		else
			this.tiles.add(tile);
		return tile;
	}

	public Tile addTile(int x, int y) {
		Tile tile = new Tile(x, y);
		if (this.tiles.contains(tile))
			return this.tiles.get(this.tiles.indexOf(tile));
		else
			this.tiles.add(tile);
		return tile;
	}
	
	public void invalidate(Knot parent) {

		Tile parentTile = this.addTile(new Tile(parent.getX(), parent.getY()));
		parentTile.setVisitedByHead(true);

		Knot child = parent.getChild();
		if (child != null) {
			Tile childTile = this.addTile(new Tile(child.getX(), child.getY()));

			if (parentTile.distance(childTile.getX(), childTile.getY()) > 1.8) {
				List<Tile> parentNeighbours = parentTile.getNeighbours();
				List<Tile> childNeighbours = childTile.getNeighbours();
				List<Tile> overlappingNeighbours = parentNeighbours.stream()
						.filter(headNeighbour -> childNeighbours.contains(headNeighbour)).toList();
				Tile tile = null;
				double minDistance = 12f;
				for (Tile suspect : overlappingNeighbours) {
					double distance = suspect.distance(parentTile.getX(), parentTile.getY());
					if(distance < minDistance) {
						tile = suspect;
						minDistance = distance;
					}
				}
				child.setX(tile.getX());
				child.setY(tile.getY());
			}
			System.out.println(this);
			this.invalidate(child);
		} else {
			parentTile.setVisitedByTail(true);
		}

	}

	@Override
	public String toString() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		
		int minX = this.tiles.stream().mapToInt(tile -> tile.getX()).min().getAsInt();
		int maxX = this.tiles.stream().mapToInt(tile -> tile.getX()).max().getAsInt();
		
		int minY = this.tiles.stream().mapToInt(tile -> tile.getY()).min().getAsInt();
		int maxY = this.tiles.stream().mapToInt(tile -> tile.getY()).max().getAsInt();
		
		Knot head = this.head;
		List<Knot> knotList = new ArrayList<>();
		knotList.add(head);
		Knot child = head.getChild();
		knotList.add(child);
		
		while(child.getChild() != null) {
			knotList.add(child.getChild());
			child = child.getChild();
		}
		
		for(int i=minX; i<=maxX; i++) {
			sb.append("=");
		}
		sb.append("\n");
		
		for(int y=maxY; y>=minY; y--) {
			for(int x=minX; x<=maxX; x++) {
				Knot knot =  null;
				for(Knot suspect: knotList) {
					if(suspect.getX() == x && suspect.getY() == y) {
						knot = suspect;
						break;
					}
				}
				sb.append(knot == null ? "." : knot.getName());
			}
			sb.append("\n");
		}
		
		for(int i=minX; i<=maxX; i++) {
			sb.append("=");
		}
		sb.append("\n");
		
//		knotList.forEach(knot -> System.out.println(String.format("%s %d %d", knot.getName(), knot.getX(), knot.getY())));
		return sb.toString();
	}
}
