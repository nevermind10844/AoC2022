package main;

import java.util.List;

import fun.learn.aoc.LineReader;

public class Main {

	public static void main(String[] args) {

		List<String> strings = LineReader.read("ext/example.txt");

		Map map = new Map();
//		strings.forEach(string -> System.out.println(string));

		for (String s : strings) {
			String[] splitString = s.split(": ");
			String[] sensorCoordString = splitString[0].replace("Sensor at ", "").split(", ");

			int sensorX = Integer.valueOf(sensorCoordString[0].replace("x=", ""));
			int sensorY = Integer.valueOf(sensorCoordString[1].replace("y=", ""));

			Sensor sensor = new Sensor(sensorX, sensorY);
			Tile sensorTile = new Tile(sensor);
			try {
				map.addTile(sensorTile);
			} catch (UnsupportedOperationException ex) {
				ex.printStackTrace();
			}
			sensorTile = map.getTile(sensorX, sensorY);

			String[] beaconCoordString = splitString[1].replace("closest beacon is at ", "").split(", ");

			int beaconX = Integer.valueOf(beaconCoordString[0].replace("x=", ""));
			int beaconY = Integer.valueOf(beaconCoordString[1].replace("y=", ""));

			Beacon beacon = new Beacon(beaconX, beaconY);
			Tile beaconTile = new Tile(beacon);
			try {
				map.addTile(beaconTile);
			} catch (UnsupportedOperationException ex) {
				ex.printStackTrace();
			}
			beaconTile = map.getTile(beaconX, beaconY);

			sensor.setBeacon(beacon);

		}
		
		map.thinAir();

		map.spreadSensors();
		
		long scanned = map.getScannedCountInLine(10);
		
		System.out.println(map);

	}

}
