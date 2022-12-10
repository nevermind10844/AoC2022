import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Forest {
	List<Knot> treeList;

	public Forest() {
		treeList = new ArrayList<>();
	}

	public List<Knot> getTreeList() {
		return treeList;
	}
	
	public void addTree(Knot tree) {
		this.treeList.add(tree);
	}
	
	public List<Knot> getTreeLine(int which, boolean row, boolean inverted) {
		List<Knot> filteredTreeLine = treeList.stream().filter(tree -> row ? tree.getRow() == which : tree.getCol() == which).toList();
		List<Knot> treeLine = new ArrayList<>();
		filteredTreeLine.forEach(tree -> treeLine.add(tree));
		if(inverted)
			Collections.reverse(treeLine);
		return treeLine;
	}

	@Override
	public String toString() {
		return "Forest [treeList=" + treeList + "]";
	}
	
}
