import java.util.ArrayList;
import java.util.List;

public class CRT {

	private List<Integer> matches;
	
	public CRT() {
		this.matches = new ArrayList<>();
	}

	public void draw(int cycle, int position) {
		cycle-=1;
		int matcher = cycle % 40;
		
		if(matcher == position-1 || matcher == position || matcher == position+1)
			matches.add(cycle);
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 40; i++)
			sb.append("=");
		sb.append("\n");

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 40; x++) {
				int pos = x + (y * 40);
				sb.append(matches.contains(pos) ? "#" : ".");
			}
			sb.append("\n");
		}

		for (int i = 0; i < 40; i++)
			sb.append("=");
		sb.append("\n");

		System.out.println(sb.toString());
	}
}
