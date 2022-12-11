package main;

import java.util.List;
import java.util.Optional;

public class Action {
	private int trueMonkey;
	private int falseMonkey;

	private List<Monkey> monkeyList;

	public Action(List<Monkey> monkeyList) {
		this.monkeyList = monkeyList;
	}

	public void setTrueMonkey(int trueMonkey) {
		this.trueMonkey = trueMonkey;
	}

	public void setFalseMonkey(int falseMonkey) {
		this.falseMonkey = falseMonkey;
	}

	public void throwItem(boolean trueMonkey, Item item) {
		
		
		Optional<Monkey> targetMonkey = monkeyList.stream()
				.filter(monkey -> (trueMonkey ? monkey.getId() == this.trueMonkey : monkey.getId() == this.falseMonkey))
				.findFirst();
		if(targetMonkey.isPresent()) {
			targetMonkey.get().addItem(item);
//			System.out.println(String.format("monkey with id %d catch", targetMonkey.get().getId()));
		} else {
//			System.err.println("now monkey found with id " + (trueMonkey ? this.trueMonkey : this.falseMonkey));
		}
	}

	@Override
	public String toString() {
		return "Action [trueMonkey=" + trueMonkey + ", falseMonkey=" + falseMonkey + "]";
	}
}
