import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();
		int sum = 0;
		for(String s : strings) {
			System.out.println(s);
			String[] split = s.split(",");
			String[] left = split[0].split("-");
			
			List<Integer> leftRange = new ArrayList<>();
			for(int i = Integer.valueOf(left[0]); i<Integer.valueOf(left[1])+1; i++) {
				leftRange.add(i);
			}
			
			String[] right = split[1].split("-");

			List<Integer> rightRange = new ArrayList<>();
			
			for(int i = Integer.valueOf(right[0]); i<Integer.valueOf(right[1])+1; i++) {
				rightRange.add(i);
			}
			
			for(int part : leftRange) {
				if(rightRange.contains(part)) {
					sum++;
					break;
				}
			}
			
		}
		System.out.println(sum); //917
	}

}
