import java.util.Objects;

public class Node {
	private Tile tile;
	private double h;
	private double f;
	private Node prevNode;
	
	public Node(Tile tile) {
		this.tile = tile;
		this.h = 0;
		this.f = 0;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	public Tile getTile() {
		return tile;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tile);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(tile, other.tile);
	}

	@Override
	public String toString() {
		return "Node [tile=" + tile + ", h=" + h + ", f=" + f + "]";
	}
	
	
}
