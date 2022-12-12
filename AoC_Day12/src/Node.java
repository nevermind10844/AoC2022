import java.util.Objects;

public class Node {
	private Tile tile;
	private double h;
	private double g;
	private Node prevNode;
	
	private boolean currentNode;
	
	private boolean solution;
	private int steps;
	
	public Node(Tile tile) {
		this.tile = tile;
		this.h = 0;
		this.g = 0;
		this.currentNode = false;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getF() {
		return g+h;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
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

	public boolean isCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(boolean currentNode) {
		this.currentNode = currentNode;
	}

	public boolean isSolution() {
		return solution;
	}

	public void setSolution(boolean solution) {
		this.solution = solution;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
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
		return "Node [tile=" + tile + ", h=" + h + ", g=" + g + ", prevNode=" + prevNode + ", currentNode="
				+ currentNode + ", solution=" + solution + ", steps=" + steps + "]";
	}
	
	
}
