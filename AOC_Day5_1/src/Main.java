import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		int strutSplitIndex = 0;
		List<String> strings = InputReader.readInput();
		for (int i = 0; i < strings.size(); i++) {
			if (strings.get(i).isEmpty()) {
				strutSplitIndex = i;
				break;
			}
		}

		Map<Integer, Stack> stacks = new HashMap<>();
		int strutIdIndex = strutSplitIndex - 1;
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

		for (int i = strutSplitIndex+1; i < strings.size(); i++) {
			String[] op = strings.get(i).split(" ");
			int count = Integer.valueOf(op[1]);
			int from = Integer.valueOf(op[3]);
			int to = Integer.valueOf(op[5]);
			Stack fromStack = stacks.get(from);
			Stack toStack = stacks.get(to);
			stacks.forEach((k, v) -> System.out.println(v));
//			for (int j = 0; j < count; j++) {
				List<String> staple = fromStack.pop(count);
				System.out.println(strings.get(i));
				toStack.push(staple);
//			}
			stacks.forEach((k, v) -> System.out.println(v));
			System.out.println();
			
		}
		
		

	}

}
