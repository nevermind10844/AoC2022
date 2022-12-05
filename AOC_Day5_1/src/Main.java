import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read();

		int strutSplitIndex = Setup.getSplitIndex(strings);

		Map<Integer, Stack> stacks = Setup.getStacks(strings, strutSplitIndex);
		
		for (int i = strutSplitIndex+1; i < strings.size(); i++) {
			String[] op = strings.get(i).split(" ");
			int count = Integer.valueOf(op[1]);
			Stack fromStack = stacks.get(Integer.valueOf(op[3]));
			Stack toStack = stacks.get(Integer.valueOf(op[5]));
			List<String> staple = fromStack.pop(count);
			toStack.push(staple);
		}
		
		List<String> result = stacks.values().stream().map(stack -> stack.getStaple().get(stack.getStaple().size()-1)).collect(Collectors.toList());
		System.out.println(result);
	}

}
