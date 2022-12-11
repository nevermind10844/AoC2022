package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fun.learn.aoc.LineReader;
import main.Operation.OperationType;

public class Main {

	public static void main(String[] args) {
		List<String> strings = LineReader.read("ext/example.txt");
		
		List<Monkey> monkeyList = new ArrayList<>();

		ReadState state = ReadState.START;
		Monkey monkey = null;
		Test test = null;
		Action action = null;

		for (String string : strings) {
			switch (state) {
			case START:
				int monkeyId = Integer.valueOf(String.valueOf(string.split(" ")[1].charAt(0)));
				monkey = new Monkey(monkeyId);
				state = ReadState.MONKEY;
				break;
			case MONKEY:
				String[] splitIds = string.split(": ")[1].split(", ");
				for (int i = 0; i < splitIds.length; i++) {
					Item item = new Item(UUID.randomUUID().toString());
					item.setWorryLevel(BigInteger.valueOf(Long.valueOf(splitIds[i])));
					monkey.addItem(item);
				}
				state = ReadState.ITEMS;
				break;
			case ITEMS:
				Operation op = null;
				String opString = string.split(" = ")[1];
				String[] opSplit = opString.split(" ");

				if (opSplit[0].equals("old") && opSplit[2].equals("old") && opSplit[1].equals("*"))
					op = new Operation(OperationType.POW, BigInteger.ZERO);
				else if (opSplit[1].equals("*"))
					op = new Operation(OperationType.MULT, BigInteger.valueOf(Long.valueOf(opSplit[2])));
				else if (opSplit[1].equals("+"))
					op = new Operation(OperationType.ADD, BigInteger.valueOf(Long.valueOf(opSplit[2])));
				else
					System.err.println("literally unsupported operation");

				monkey.setOp(op);
				state = ReadState.OPERATION;
				break;
			case OPERATION:
				int testValue = Integer.valueOf(string.trim().split(" ")[3]);
				test = new Test(testValue);
				monkey.setTest(test);
				state = ReadState.TEST;
				break;
			case TEST:
				int trueMonkeyId = Integer.valueOf(string.trim().split(" ")[5]);
				action = new Action(monkeyList);
				monkey.setAction(action);
				action.setTrueMonkey(trueMonkeyId);
				state = ReadState.ACTION_TRUE;
				break;
			case ACTION_TRUE:
				int falseMonkeyId = Integer.valueOf(string.trim().split(" ")[5]);
				action.setFalseMonkey(falseMonkeyId);
				state = ReadState.ACTION_FALSE;
				
				monkeyList.add(monkey);
				monkey = null;
				test = null;
				action = null;
				
				break;
			case ACTION_FALSE:
				state = ReadState.START;
				break;
			}
		}
		
		for(int i=0; i<10000; i++) {
			System.out.println("round " + i + " in progress...");
			monkeyList.forEach(m -> m.inspectItems());
		}
		
		System.out.println(monkeyList);
	}

	private enum ReadState {
		START, MONKEY, ITEMS, OPERATION, TEST, ACTION_TRUE, ACTION_FALSE
	}

}
