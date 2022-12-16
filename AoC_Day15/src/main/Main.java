package main;

import java.util.ArrayList;
import java.util.List;

import fun.learn.aoc.LineReader;

public class Main {

	public static void main(String[] args) {

		List<String> strings = LineReader.read("ext/input.txt");

//		Map map = new Map();
//		strings.forEach(string -> System.out.println(string));

		List<Sensor> sensorList = new ArrayList<>();

		for (String s : strings) {
			String[] splitString = s.split(": ");
			String[] sensorCoordString = splitString[0].replace("Sensor at ", "").split(", ");

			int sensorX = Integer.valueOf(sensorCoordString[0].replace("x=", ""));
			int sensorY = Integer.valueOf(sensorCoordString[1].replace("y=", ""));

			Sensor sensor = new Sensor(sensorX, sensorY);
//			Tile sensorTile = new Tile(sensor);
//			try {
//				map.addTile(sensorTile);
//			} catch (UnsupportedOperationException ex) {
//				ex.printStackTrace();
//			}
//			sensorTile = map.getTile(sensorX, sensorY);

			String[] beaconCoordString = splitString[1].replace("closest beacon is at ", "").split(", ");

			int beaconX = Integer.valueOf(beaconCoordString[0].replace("x=", ""));
			int beaconY = Integer.valueOf(beaconCoordString[1].replace("y=", ""));

			Beacon beacon = new Beacon(beaconX, beaconY);
//			Tile beaconTile = new Tile(beacon);
//			try {
//				map.addTile(beaconTile);
//			} catch (UnsupportedOperationException ex) {
//				ex.printStackTrace();
//			}
//			beaconTile = map.getTile(beaconX, beaconY);

			sensor.setBeacon(beacon);

			sensorList.add(sensor);
		}

		int minSensorX = sensorList.stream().mapToInt(sensor -> sensor.getX()).min().getAsInt();
		int minBeaconX = sensorList.stream().mapToInt(sensor -> sensor.getBeacon().getX()).min().getAsInt();
		int minX = minSensorX < minBeaconX ? minSensorX : minBeaconX;

		int realMinX = minX;

		for (Sensor sensor : sensorList) {
			int manhattenDistance = sensor.getManhattenDistance(sensor.getBeacon());
			if (sensor.getX() - manhattenDistance < realMinX)
				realMinX = sensor.getX() - manhattenDistance;
		}

		int maxSensorX = sensorList.stream().mapToInt(sensor -> sensor.getX()).max().getAsInt();
		int maxBeaconX = sensorList.stream().mapToInt(sensor -> sensor.getBeacon().getX()).max().getAsInt();
		int maxX = maxSensorX > maxBeaconX ? maxSensorX : maxBeaconX;

		int realMaxX = maxX;

		for (Sensor sensor : sensorList) {
			int manhattenDistance = sensor.getManhattenDistance(sensor.getBeacon());
			if (sensor.getX() + manhattenDistance > realMaxX)
				realMaxX = sensor.getX() + manhattenDistance;
		}

		System.out.println(maxX - minX);
//		int y = 2000000;
//		int sum = 0;
		
//		int resultX = -1;
//		int resultY = -1;
		
		ThreadPool threadPool = new ThreadPool();
		
		Thread threadPoolThread = new Thread(threadPool);
		threadPoolThread.start();
		
		int size = 4000000;
		int divisions = 400;
		int length = size / divisions;
		
		
		for(int y=0; y<divisions; y++) {
			for(int x=0; x<divisions; x++) {
				System.out.println(x + ":" + y);
				System.out.println(length*x + ":" + length*y);
				QuickMath qm = new QuickMath(length*x, length*y, length, sensorList);
				Thread t = new Thread(qm);
				t.setName("thread " + y);
				t.start();
				while(threadPool.isFull()) {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				threadPool.addRunnableAndStartThread(qm);
			}
		}

//		map.thinAir();
//
//		map.spreadSensors();
//		
//		long scanned = map.getScannedCountInLine(10);
//		
//		System.out.println(map);

	}

}
