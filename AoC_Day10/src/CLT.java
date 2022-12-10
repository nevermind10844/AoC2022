public class CLT {
	private int cycle = 0;
	private int x = 1;
	private int sum = 0;
	private CRT crt;

	public CLT() {
		this.cycle = 0;
		this.x = 1;
		this.sum = 0;
		this.crt = new CRT();
	}

	public int getSum() {
		return this.sum;
	}

	public void cycle(String cmd) {
		String[] splitCmd = cmd.split(" ");
		switch (splitCmd[0]) {
		case "noop":
			cycle++;
			crt.draw(cycle, x);
			break;
		case "addx":
			cycle++;
			crt.draw(cycle, x);
			cycle++;
			crt.draw(cycle, x);
			x += Integer.valueOf(splitCmd[1]);
			break;
		}

	}
}
