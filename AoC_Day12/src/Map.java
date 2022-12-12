import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Map {
	private List<Tile> tileList;

	public Map() {
		tileList = new ArrayList<>();
	}

	public List<Tile> getTileList() {
		return this.tileList;
	}

	public void addTile(Tile tile) {
		this.tileList.add(tile);
	}

	public Tile getTile(int x, int y) {
		Tile t = new Tile(x, y);
		int index = this.tileList.indexOf(t);
		if (index < 0)
			throw new NoSuchElementException();
		return this.tileList.get(index);
	}

	@Override
	public String toString() {

		Node currentNode = this.tileList.stream().filter(tile -> tile.getNode().isCurrentNode()).findFirst().get()
				.getNode();

		StringBuilder sb = new StringBuilder();

		int maxX = this.tileList.stream().mapToInt(Tile::getX).max().getAsInt();
		int minX = this.tileList.stream().mapToInt(Tile::getX).min().getAsInt();
		int maxY = this.tileList.stream().mapToInt(Tile::getY).max().getAsInt();
		int minY = this.tileList.stream().mapToInt(Tile::getY).min().getAsInt();

		for (int y = minY; y < maxY + 1; y++) {
			for (int x = minX; x < maxX + 1; x++) {
				Tile t = this.getTile(x, y);

				if (t.getNode().equals(currentNode)) {
					sb.append(" X ");
				} else {
					Node n = currentNode.getPrevNode();
					boolean found = false;
					if(n != null) {
						while(n.getPrevNode() != null) {
							n = n.getPrevNode();
							if(n.getTile().equals(t)) {
								sb.append(" * ");
								found = true;
							}
						}
					}
					if(!found) {
						if (t.isStart() || t.isEnd())
							sb.append(String.format(" %s ", t.isStart() ? "S" : "E"));
						else
							sb.append(String.format("%2d ", t.getHeight()));
					}
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
