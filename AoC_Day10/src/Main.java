import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<String> commands = LineReader.read();
		CLT clt = new CLT();
		
		for(int i=0; i< commands.size(); i++) {
			clt.cycle(commands.get(i));
		}
		
		System.out.println(clt.getSum());
	}

}
