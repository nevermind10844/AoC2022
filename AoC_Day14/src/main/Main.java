package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fun.learn.aoc.LineReader;
import main.Tile.TileType;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read("ext/input.txt");
//		strings.forEach(string -> System.out.println(string));
		HashMap<Integer, List<String>> formationLines = new HashMap<>();

		for (int i = 0; i < strings.size(); i++) {
			List<String> formation = Arrays.asList(strings.get(i).split(" -> "));
			formationLines.put(i, formation);
		}

		Map map = new Map();

		for (int i = 0; i < formationLines.size(); i++) {
			List<String> formationLine = formationLines.get(i);
			System.out.println(formationLine);
			for (int j = 0; j < formationLine.size() - 1; j++) {
				int xStart = Integer.valueOf(formationLine.get(j).split(",")[0]);
				int yStart = Integer.valueOf(formationLine.get(j).split(",")[1]);
				int xEnd = Integer.valueOf(formationLine.get(j + 1).split(",")[0]);
				int yEnd = Integer.valueOf(formationLine.get(j + 1).split(",")[1]);

				if(xStart > xEnd) {
					int xTemp = xStart;
					xStart = xEnd;
					xEnd = xTemp;
				}
				
				if(yStart > yEnd) {
					int yTemp = yStart;
					yStart = yEnd;
					yEnd = yTemp;
				}
				
				for (int x = xStart; x <= xEnd; x++) {
					for (int y = yStart; y <= yEnd; y++) {
						try {
							Tile t = new Tile(x, y);
							t.setTileType(TileType.ROCK);
							map.addTile(t);
						} catch (UnsupportedOperationException ex) {

						}
					}
				}
			}
		}

		Tile spawn = new Tile(500, 0);
		map.setSpawn(spawn);

		map.thinAir();
		
		try {
			while (!map.getEndOfGame()) {
				map.invalidate();
//				System.out.println(map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println(map);
		
		System.out.println(map.getSandBlockCount());
	}

}
