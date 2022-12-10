public class Knot {
	private String name;
	private int x;
	private int y;
	private Knot child;

	public Knot(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public void moveUp() {
		this.y++;
	}

	public void moveDown() {
		this.y--;
	}

	public void moveLeft() {
		this.x--;
	}

	public void moveRight() {
		this.x++;
	}

	public Knot getChild() {
		return child;
	}

	public void setChild(Knot child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "name=" + name + ", x=" + x + ", y=" + y + ", child=" + child;
	}

}
