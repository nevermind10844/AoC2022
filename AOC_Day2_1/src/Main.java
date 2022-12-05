import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Main {
	
	public static void main(String[] args) {
		List<String> strings = InputReader.readInput();
		List<Entry<Thing, Thing>> gameData = Structure.translate(strings);
		
		List<Integer> results = new ArrayList<>();
		gameData.forEach(entry -> results.add(Structure.compareResult(entry.getKey(), entry.getValue()) + entry.getValue().getValue()));

		System.out.println(results.stream().mapToInt(Integer::intValue).sum());
	}
}
