import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		List<Entry<List<Integer>, List<Integer>>> rucksackList = new ArrayList<>();
		int sum = 0;

		for (String s : strings) {
			CharSequence left = s.subSequence(0, s.length());
//			CharSequence right = s.subSequence(s.length() / 2, s.length());

			List<Integer> leftCompartment = new ArrayList<>();
			List<Integer> rightCompartment = new ArrayList<>();

			Entry<List<Integer>, List<Integer>> rucksack = new SimpleEntry<>(leftCompartment, rightCompartment);
			rucksackList.add(rucksack);

			left.chars().forEach(item -> leftCompartment.add(item > 96 ? item - 96 : item - 38));
//			right.chars().forEach(item -> rightCompartment.add(item > 96 ? item - 96 : item - 38));

//			for(int i=0; i<leftCompartment.size(); i++) {
//				Integer cur = leftCompartment.get(i);
//				if(rightCompartment.contains(cur)) {
//					sum += cur;
//					break;
//				}
//			}
		}

		int groupCount = strings.size() / 3;
		for (int i = 0; i < groupCount; i++) {
			List<Entry<List<Integer>, List<Integer>>> currentGroup = rucksackList.subList(i * 3, i * 3 + 3);
			for (int j = 0; j < currentGroup.get(0).getKey().size(); j++) {
				Integer cur = currentGroup.get(0).getKey().get(j);
				if (currentGroup.get(1).getKey().contains(cur) && currentGroup.get(2).getKey().contains(cur)) {
					sum += cur;
					break;
				}
			}
		}

		System.out.println(sum);
	}

}
