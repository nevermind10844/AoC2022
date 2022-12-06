public class Main {

	public static void main(String[] args) {
		String string = LineReader.read().get(0);
		for(int v=0; v<string.length()-14; v++) {
			String substring = string.substring(v, v+14);
			if(substring.chars().distinct().count() == 14) {
				System.out.println(v+14);
				break;
			}
		}
	}
}
