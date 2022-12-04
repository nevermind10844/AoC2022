import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		List<Integer> sums = new ArrayList<>();
		
		int sum = 0;
		for(String s : strings) {
			if(!s.isEmpty()) {
				sum += Integer.valueOf(s);
			} else {
				sums.add(sum);
				sum = 0;
			}
		}
		sums.add(sum);
		
		Collections.sort(sums, Collections.reverseOrder());
		int result = sums.get(0) + sums.get(1) + sums.get(2);
		
		System.out.println(result);
	}

}
