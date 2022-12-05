
public enum Thing {
	ROCK(1, "A", "X", "Rock"),
	PAPER(2, "B", "Y", "Paper"),
	SCISSORS(3, "C", "Z", "Scissors");
	
	private int value;
	private String a;
	private String b;
	private String text;
	
	private Thing(int value, String a, String b, String text) {
		this.a = a;
		this.b = b;
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}

	public String getText() {
		return text;
	}
	
	public static Thing winsAgainst(Thing a) {
		if(a.equals(Thing.ROCK))
			return Thing.PAPER;
		else if(a.equals(Thing.PAPER))
			return Thing.SCISSORS;
		else 
			return Thing.ROCK;
	}
	
	public static Thing losesTo(Thing a) {
		if(a.equals(Thing.ROCK))
			return Thing.SCISSORS;
		else if(a.equals(Thing.PAPER))
			return Thing.ROCK;
		else 
			return Thing.PAPER;
	}
	
	public static Thing drawsWith(Thing a) {
		return a;
	}
	
}
