import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fun.learn.aoc.LineReader;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read("ext/example.txt");
		
		List<Node> unknownList = new ArrayList<>();
		List<Node> openList = new ArrayList<>();
		List<Node> closedList = new ArrayList<>();

		Map map = new Map();

		for (int y = 0; y < strings.size(); y++) {
			List<Integer> heights = strings.get(y).chars().boxed()
					.mapToInt(val -> val > 96 ? val - 96 : val == 83 ? -1 : -2).boxed().collect(Collectors.toList());
			for (int x = 0; x < heights.size(); x++) {
				Tile tile = new Tile(x, y);
				tile.setHeight(heights.get(x));
				tile.setStart(tile.getHeight() == -1);
				tile.setEnd(tile.getHeight() == -2);
				tile.setHeight(tile.isStart() ? 1 : (tile.isEnd() ? 26 : tile.getHeight()));
				map.addTile(tile);
			}
		}
		
		for (Tile  tile : map.getTileList()) {
			Node n = new Node(tile);
			openList.add(n);
		}
		
		System.out.println(map);
	}
	
	private static void expandNode(Node node, List<Node> allNodes, List<Node> openList, List<Node> closedList) {
		List<Node> neighbours = getNeighbors(node, openList);
	}
	
	private static List<Node> getNeighbors(Node node, List<Node> openList){
		int x = node.getTile().getX();
		int y = node.getTile().getY();
		
		Tile leftTile = new Tile(x-1, y);
		Node leftNode = new Node(leftTile);
		
		Optional<Node> potLeftNode = openList.stream().filter(n -> n.equals(leftNode)).findFirst();
		
		Tile rightTile = new Tile(x+1, y);
		Node rightNode = new Node(rightTile);
		
		Optional<Node> potRightNode = openList.stream().filter(n -> n.equals(leftNode)).findFirst();
		
		Tile topTile = new Tile(x, y-1);
		Node topNode = new Node(topTile);
		
		Optional<Node> potTopNode = openList.stream().filter(n -> n.equals(leftNode)).findFirst();
		
		Tile botTile = new Tile(x-1, y+1);
		Node botNode = new Node(botTile);
		
		Optional<Node> potBotNode = openList.stream().filter(n -> n.equals(leftNode)).findFirst();
		
		List<Node> neighbours = new ArrayList<>();
		if(potRightNode.isPresent())
			neighbours.add(potRightNode.get());
		if(potLeftNode.isPresent())
			neighbours.add(potLeftNode.get);
		if(potTopNode.isPresent())
			neighbours.add(potTopNode.get());
		if(potBotNode.isPresent())
			neighbours.add(potBotNode.get());
		
		return neighbours;
	}

}
