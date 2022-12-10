import java.util.List;

import fun.learn.aoc.LineReader;

public class Main {

	public static void main(String[] args) {
		List<String> commands = LineReader.read("ext/input.txt");
		CLT clt = new CLT();
		
		for(int i=0; i< commands.size(); i++) {
			clt.cycle(commands.get(i));
		}
		
//		System.out.println(clt.getSum());
	}

}
