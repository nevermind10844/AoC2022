import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		List<IntStream> rucksackList = new ArrayList<>();

		strings.forEach(s -> rucksackList.add(s.chars().map(item -> item > 96 ? item - 96 : item - 38)));

		int sum = 0;
		for (int i = 0; i < strings.size(); i+=3) {
			System.out.println(i);
			List<IntStream> currentGroup = rucksackList.subList(i, i + 3);
			List<Integer> masterList = currentGroup.get(0).boxed().toList();
			List<Integer> firstSample = currentGroup.get(1).boxed().toList();
			List<Integer> secondSample = currentGroup.get(2).boxed().toList();
			
			for(int probe : masterList) {
				if(firstSample.contains(probe) && secondSample.contains(probe)) {
					sum += probe;
					break;
				}
			}
		}

		System.out.println(sum);
	}
	
}
