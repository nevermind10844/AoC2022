import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Setup {
	public static int getSplitIndex(List<String> strings) {
		int strutSplitIndex = 0;
		for (int i = 0; i < strings.size(); i++) {
			if (strings.get(i).isEmpty()) {
				strutSplitIndex = i;
				break;
			}
		}
		return strutSplitIndex;
	}

	public static Map<Integer, Stack> getStacks(List<String> strings, int strutSplitIndex) {
		int strutIdIndex = strutSplitIndex - 1;
		
		Map<Integer, Stack> stacks = new HashMap<>();
		
		for (int i = 0; i < strings.get(strutIdIndex).length(); i++) {
			if (strings.get(strutIdIndex).charAt(i) != ' ') {
				Stack s = new Stack();
				s.setId(Integer.valueOf(String.valueOf(strings.get(strutIdIndex).charAt(i))));
				s.setIndex(i);
				s.setStaple(new ArrayList<String>());
				stacks.put(s.getId(), s);
			}
		}
		
		for (Entry<Integer, Stack> entry : stacks.entrySet()) {
			Stack stack = entry.getValue();
			int index = stack.getIndex();
			for (int i = strutIdIndex - 1; i >= 0; i--) {
				char c = strings.get(i).charAt(index);
				if (c != ' ')
					stack.getStaple().add(String.valueOf(c));
			}
		}
		
		return stacks;
	}
}
