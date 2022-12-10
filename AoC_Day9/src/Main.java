import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		
		Knot head = new Knot(12, 6, "H");
		Knot child = new Knot(12, 6, "1");
		head.setChild(child);
		for(int i=2; i<10; i++) {
			child.setChild(new Knot(12, 6, String.valueOf(i)));
			child = child.getChild();
		}
		
		Board board = new Board(head);
		board.invalidate(head);
		
		for (String string : strings) {
			String[] command = string.split(" ");
			switch(command[0]) {
			case "L":
				for(int i=0; i<Integer.valueOf(command[1]); i++) {
					head.moveLeft();
					board.invalidate(head);
				}
				break;
			case "R":
				for(int i=0; i<Integer.valueOf(command[1]); i++) {
					head.moveRight();
					board.invalidate(head);
				}
				break;
			case "U":
				for(int i=0; i<Integer.valueOf(command[1]); i++) {
					head.moveUp();
					board.invalidate(head);
				}
				break;
			case "D":
				for(int i=0; i<Integer.valueOf(command[1]); i++) {
					head.moveDown();
					board.invalidate(head);
				}
				break;
			}
		}
		
		List<Tile> tiles = board.getTiles();
//		tiles.stream().filter(tile -> tile.isVisitedByTail()).toList().forEach(tile -> System.out.println(tile));
		
		int count = tiles.stream().filter(tile -> tile.isVisitedByTail()).toList().size();
		System.out.println(count);
		
//		System.out.println(board);
	}

}
