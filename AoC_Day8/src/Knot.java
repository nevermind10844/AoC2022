
public class Knot {
	private int height;
	private boolean visibleFromSouth;
	private boolean visibleFromNorth;
	private boolean visibleFromEast;
	private boolean visibleFromWest;
	private int row;
	private int col;
	private int visionScore;

	public Knot() {
		this.visibleFromSouth = false;
		this.visibleFromNorth = false;
		this.visibleFromEast = false;
		this.visibleFromWest = false;
		this.visionScore = 0;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVisibleFromSouth() {
		return visibleFromSouth;
	}

	public void setVisibleFromSouth(boolean visibleFromSouth) {
		this.visibleFromSouth = visibleFromSouth;
	}

	public boolean isVisibleFromNorth() {
		return visibleFromNorth;
	}

	public void setVisibleFromNorth(boolean visibleFromNorth) {
		this.visibleFromNorth = visibleFromNorth;
	}

	public boolean isVisibleFromEast() {
		return visibleFromEast;
	}

	public void setVisibleFromEast(boolean visibleFromEast) {
		this.visibleFromEast = visibleFromEast;
	}

	public boolean isVisibleFromWest() {
		return visibleFromWest;
	}

	public void setVisibleFromWest(boolean visibleFromWest) {
		this.visibleFromWest = visibleFromWest;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isVisible() {
		return visibleFromEast || visibleFromWest || visibleFromNorth || visibleFromSouth;
	}

	public int getVisionScore() {
		return visionScore;
	}

	public void setVisionScore(int visionScore) {
		this.visionScore = visionScore;
	}

	@Override
	public String toString() {
		return "Tree [height=" + height + ", visibleFromSouth=" + visibleFromSouth + ", visibleFromNorth="
				+ visibleFromNorth + ", visibleFromEast=" + visibleFromEast + ", visibleFromWest=" + visibleFromWest
				+ ", row=" + row + ", col=" + col + ", visionScore=" + visionScore + "]";
	}

}
