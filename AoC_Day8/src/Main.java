import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		Forest forest = new Forest();
		int height = strings.size();
		int width = strings.get(0).length();
		for (int y = 0; y < height; y++) {
			String s = strings.get(y);
			for (int x = 0; x < width; x++) {
				char c = s.charAt(x);
				Knot t = new Knot();
				t.setHeight(Integer.valueOf(String.valueOf(c)));
				t.setCol(x);
				t.setRow(y);
				forest.addTree(t);
			}
		}

		for (int x = 0; x < width; x++) {
			List<Knot> treeColumn = forest.getTreeLine(x, false, false);
			int maxHeight = -1;
			for (Knot tree : treeColumn) {
				if (tree.getHeight() > maxHeight) {
					tree.setVisibleFromNorth(true);
					maxHeight = tree.getHeight();
				}
			}

			treeColumn = forest.getTreeLine(x, false, true);
			maxHeight = -1;
			for (Knot tree : treeColumn) {
				if (tree.getHeight() > maxHeight) {
					tree.setVisibleFromSouth(true);
					maxHeight = tree.getHeight();
				}
			}
		}

		for (int y = 0; y < width; y++) {
			List<Knot> treeRow = forest.getTreeLine(y, true, false);
			int maxHeight = -1;
			for (Knot tree : treeRow) {
				if (tree.getHeight() > maxHeight) {
					tree.setVisibleFromNorth(true);
					maxHeight = tree.getHeight();
				}
			}

			treeRow = forest.getTreeLine(y, true, true);
			maxHeight = -1;
			for (Knot tree : treeRow) {
				if (tree.getHeight() > maxHeight) {
					tree.setVisibleFromSouth(true);
					maxHeight = tree.getHeight();
				}
			}
		}

//		for(int y=0; y<height; y++) {
//			System.out.print(y + " ");
//			List<Tree> treeRow = forest.getTreeLine(y, true, false);
//			treeRow.forEach(tree -> System.out.print(tree.getHeight() + "(" + tree.isVisible() + ")"));
//			System.out.println();
//		}

//		int visibleCount = forest.getTreeList().stream().filter(tree -> tree.isVisible()).toList().size();
//		System.out.println(visibleCount);

		List<Knot> visibleTrees = forest.getTreeList().stream().filter(tree -> tree.isVisible()).toList();

		for (Knot tree : visibleTrees) {
			int row = tree.getRow();
			int col = tree.getCol();
			int treeHeight = tree.getHeight();

//			System.out.println(col + " " + row + " " + treeHeight);

			int northScore = 0;
			List<Knot> forestColumn = forest.getTreeLine(col, false, false);
			for (int y=row-1; y>=0; y--) {
				//System.out.println(y + " " + forestColumn.get(y).getHeight());
				northScore++;
				if (forestColumn.get(y).getHeight() >= treeHeight) {
					break;
				}
			}

			int southScore = 0;
			for (int y=row+1; y<forestColumn.size(); y++) {
//				System.out.println(y + " " + forestColumn.get(y).getHeight());
				southScore++;
				if (forestColumn.get(y).getHeight() >= treeHeight) {
					break;
				}
			}
			
			int westScore = 0;
			List<Knot> forestRow = forest.getTreeLine(row, true, false);
			for (int x=col-1; x>=0; x--) {
//				System.out.println(x + " " + forestRow.get(x).getHeight());
				westScore++;
				if (forestRow.get(x).getHeight() >= treeHeight) {
					break;
				}
			}
			
			int eastScore = 0;
			for (int x=col+1; x<forestRow.size(); x++) {
				eastScore++;
				if (forestRow.get(x).getHeight() >= treeHeight) {
					break;
				}
			}
			
			tree.setVisionScore(northScore * westScore * southScore * eastScore);
//			
//			System.out.println(northScore + " " + westScore + " " + southScore + " " + eastScore);
//			System.out.println(northScore * westScore * southScore * eastScore);
//			System.out.println();
		}
		
		int maxVisionScore = forest.getTreeList().stream().filter(tree -> tree.isVisible()).mapToInt(tree -> tree.getVisionScore()).max().getAsInt();
		System.out.println(maxVisionScore);
	}

}
