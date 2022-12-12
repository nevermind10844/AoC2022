import java.util.Objects;

public class Tile {
	private int x;
	private int y;
	private int height;
	
	private boolean start;
	private boolean end;
	
	private Node n;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		n = null;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
	
	public void setNode(Node n) {
		this.n = n;
	}
	
	public Node getNode() {
		return this.n;
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

	@Override
	public String toString() {
		return "Tile [x=" + x + ", y=" + y + ", height=" + height + "]";
	}
	
}
