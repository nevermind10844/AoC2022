import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fun.learn.aoc.LineReader;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read("ext/input.txt");

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

		List<Tile> lowestTiles = map.getTileList().stream().filter(tile -> tile.getHeight() == 1).toList();
		lowestTiles.stream().forEach(tile -> tile.setStart(false));
		
		List<Node> resultNodes = new ArrayList<>();

		for (int j = 0; j < lowestTiles.size(); j++) {
			List<Node> unknownList = new ArrayList<>();
			List<Node> openList = new ArrayList<>();
			List<Node> closedList = new ArrayList<>();

			lowestTiles.stream().forEach(tile -> tile.setStart(false));
			lowestTiles.get(j).setStart(true);

			for (Tile tile : map.getTileList()) {
				Node n = new Node(tile);
				tile.setNode(n);
				unknownList.add(n);
			}

			Node start = unknownList.stream().filter(node -> node.getTile().isStart()).findFirst().get();
			Node target = unknownList.stream().filter(node -> node.getTile().isEnd()).findFirst().get();

			start.setG(0);
			openList.add(start);

			Node currentNode = null;

			while (openList.size() > 0) {
				openList.sort(Comparator.comparing(Node::getF));
				currentNode = openList.get(0);
				currentNode.setCurrentNode(true);
				openList.remove(currentNode);

				if (currentNode.getTile().isEnd())
					break;

				closedList.add(currentNode);

				expandNode(currentNode, unknownList, openList, closedList, target);
//				System.out.println(map.toString());
				currentNode.setCurrentNode(false);
			}

			Node n = currentNode;
			int length = 0;
			while (n.getPrevNode() != null) {
				length++;
				n = n.getPrevNode();
			}

			boolean solution = currentNode.equals(target);
			
			start.setSolution(solution);
			start.setSteps(length);
			
			if(solution)
				resultNodes.add(start);
		}
		

		
		resultNodes.sort(Comparator.comparingInt(node -> node.getSteps()));
		System.out.println(resultNodes.get(0));
	}

	private static void expandNode(Node node, List<Node> unknownList, List<Node> openList, List<Node> closedList,
			Node target) {
		List<Node> neighbours = getNeighbours(node, unknownList, target);

		for (Node successor : neighbours) {
			if (closedList.contains(successor))
				continue;

			double g = node.getG() + 1;
			if (openList.contains(successor) && g >= successor.getG())
				continue;

			successor.setPrevNode(node);
			successor.setG(g);

			successor.setH(getDistance(successor, target));

			if (!openList.contains(successor))
				openList.add(successor);
		}
	}

	private static List<Node> getNeighbours(Node node, List<Node> unkownList, Node target) {
		int x = node.getTile().getX();
		int y = node.getTile().getY();

		Tile leftTile = new Tile(x - 1, y);
		Node leftNode = new Node(leftTile);

		Optional<Node> potLeftNode = unkownList.stream().filter(n -> n.equals(leftNode)).findFirst();

		Tile rightTile = new Tile(x + 1, y);
		Node rightNode = new Node(rightTile);

		Optional<Node> potRightNode = unkownList.stream().filter(n -> n.equals(rightNode)).findFirst();

		Tile topTile = new Tile(x, y - 1);
		Node topNode = new Node(topTile);

		Optional<Node> potTopNode = unkownList.stream().filter(n -> n.equals(topNode)).findFirst();

		Tile botTile = new Tile(x, y + 1);
		Node botNode = new Node(botTile);

		Optional<Node> potBotNode = unkownList.stream().filter(n -> n.equals(botNode)).findFirst();

		List<Node> neighbours = new ArrayList<>();
		if (potRightNode.isPresent())
			neighbours.add(potRightNode.get());
		if (potLeftNode.isPresent())
			neighbours.add(potLeftNode.get());
		if (potTopNode.isPresent())
			neighbours.add(potTopNode.get());
		if (potBotNode.isPresent())
			neighbours.add(potBotNode.get());

		for (int i = neighbours.size() - 1; i >= 0; i--) {
			Node neighbour = neighbours.get(i);
			if (neighbour.getTile().getHeight() - node.getTile().getHeight() > 1) {
				neighbours.remove(neighbour);
			}
		}

		for (Node n : neighbours) {
			node.setH(getDistance(n, target));
		}

		return neighbours;
	}

	private static double getDistance(Node n1, Node n2) {
		return Math.sqrt((n1.getTile().getX() - n2.getTile().getX()) * (n1.getTile().getX() - n2.getTile().getX())
				+ (n1.getTile().getY() - n2.getTile().getY()) * (n1.getTile().getY() - n2.getTile().getY()));
	}

}
