import java.util.List;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		int sum = 0;
		for (String s : strings) {
			String[] split = s.split(",");

			String[] left = split[0].split("-");
			List<Integer> leftRange = IntStream.range(Integer.valueOf(left[0]), Integer.valueOf(left[1]) + 1).boxed()
					.toList();

			String[] right = split[1].split("-");
			List<Integer> rightRange = IntStream.range(Integer.valueOf(right[0]), Integer.valueOf(right[1]) + 1).boxed()
					.toList();
			
			for (int part : leftRange) {
				if (rightRange.contains(part)) {
					sum++;
					break;
				}
			}
		}
		System.out.println(sum); // 917
	}

}
