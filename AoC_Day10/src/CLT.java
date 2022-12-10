import java.util.Arrays;
import java.util.List;

public class CLT {
	private int cycle = 0;
	private int x = 1;
	private int sum = 0;
	private List<Integer> markers;
	
	public CLT() {
		this.markers = Arrays.asList(20, 60, 100, 140, 180, 220);
		this.cycle = 0;
		this.x = 1;
		this.sum = 0;
	}
	
	public int getSum() {
		return this.sum;
	}

	public void cycle(String cmd) {
		String[] splitCmd = cmd.split(" ");
		switch (splitCmd[0]) {
		case "noop":
			cycle++;
			if (markers.contains(cycle)) {
				sum += cycle * x;
				System.out.println(String.format("%d %d %d", cycle, x, cycle * x));
			}
			break;
		case "addx":
			cycle++;
			if (markers.contains(cycle)) {
				sum += cycle * x;
				System.out.println(String.format("%d %d %d", cycle, x, cycle * x));
			}
			System.out.println(String.format("%d %d %d", cycle, x, cycle * x));
			cycle++;
			if (markers.contains(cycle)) {
				sum += cycle * x;
				System.out.println(String.format("%d %d %d", cycle, x, cycle * x));
			}
			System.out.println(String.format("%d %d %d", cycle, x, cycle * x));
			x += Integer.valueOf(splitCmd[1]);
			break;
		}

	}
}
