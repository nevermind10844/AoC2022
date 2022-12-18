package main;

import java.util.ArrayList;
import java.util.List;

import fun.learn.aoc.LineReader;

public class Main {

	public static void main(String[] args) {

		List<String> strings = LineReader.read("ext/input.txt");

		List<Sensor> sensorList = new ArrayList<>();

		for (String s : strings) {
			String[] splitString = s.split(": ");
			String[] sensorCoordString = splitString[0].replace("Sensor at ", "").split(", ");

			int sensorX = Integer.valueOf(sensorCoordString[0].replace("x=", ""));
			int sensorY = Integer.valueOf(sensorCoordString[1].replace("y=", ""));

			Sensor sensor = new Sensor(sensorX, sensorY);

			String[] beaconCoordString = splitString[1].replace("closest beacon is at ", "").split(", ");

			int beaconX = Integer.valueOf(beaconCoordString[0].replace("x=", ""));
			int beaconY = Integer.valueOf(beaconCoordString[1].replace("y=", ""));

			Beacon beacon = new Beacon(beaconX, beaconY);

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

		ThreadPool threadPool = new ThreadPool();

		Thread threadPoolThread = new Thread(threadPool);
		threadPoolThread.start();

		int size = 4000000;

		for (int y = 0; y < size; y++) {
			if ((y + 1) % 1000 == 0)
				System.out.println(String.format("%.3f", (y + 1) / Double.valueOf(size)));
			QuickMath qm = new QuickMath(y, size, sensorList);
			while (threadPool.isFull()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			threadPool.addRunnableAndStartThread(qm);
		}

	}

}
