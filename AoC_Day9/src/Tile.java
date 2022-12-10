import java.util.ArrayList;
import java.util.List;

public class Tile {
	private int x;
	private int y;
	private boolean visitedByHead;
	private boolean visitedByTail;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.visitedByHead = false;
		this.visitedByTail = false;
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

	public boolean isVisitedByHead() {
		return visitedByHead;
	}

	public void setVisitedByHead(boolean visitedByHead) {
		this.visitedByHead = visitedByHead;
	}

	public boolean isVisitedByTail() {
		return visitedByTail;
	}

	public void setVisitedByTail(boolean visitedByTail) {
		this.visitedByTail = visitedByTail;
	}

	// relies heavily on everything else working...
	public double distance(int x, int y) {
		return Math.sqrt((this.x-x) * (this.x-x) + (this.y-y) * (this.y-y));
//		if (this.equals(new Tile(x, y)))
//			return 0;
//		if (x == this.x && y != this.y || x != this.x && y == this.y)
//			return 1;
//		else
//			return 1.4;
	}

	public List<Tile> getNeighbours() {
		List<Tile> neighbours = new ArrayList<>();
		neighbours.add(new Tile(this.x-1, this.y+1));
		neighbours.add(new Tile(this.x, this.y+1));
		neighbours.add(new Tile(this.x+1, this.y+1));
		neighbours.add(new Tile(this.x-1, this.y));
		neighbours.add(new Tile(this.x+1, this.y));
		neighbours.add(new Tile(this.x-1, this.y-1));
		neighbours.add(new Tile(this.x, this.y-1));
		neighbours.add(new Tile(this.x+1, this.y-1));
		return neighbours;
	}

	@Override
	public String toString() {
		return "Tile [x=" + x + ", y=" + y + ", visitedByHead=" + visitedByHead + ", visitedByTail=" + visitedByTail
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tile) {
			Tile other = (Tile) obj;
			if (other.getX() == this.x && other.getY() == this.y)
				return true;
		}
		return false;
	}
}
