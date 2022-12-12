package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey {
	private int id;
	private List<Item> itemList;
	private Operation op;
	private Test test;
	private Action action;

	private int inspectionCount;

	public Monkey(int id) {
		this.id = id;
		this.itemList = new ArrayList<>();
		this.op = null;
		this.test = null;
		this.action = null;
	}

	public int getId() {
		return this.id;
	}

	public void setOp(Operation op) {
		this.op = op;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void addItem(Item item) {
//		System.out.println(
//				String.format("monkey with id %d catches item with a worrylevel of %d", this.id, item.getWorryLevel()));
		this.itemList.add(item);
	}

	public int getInspectionCount() {
		return this.inspectionCount;
	}

	public void inspectItems(int round) {
//		System.out.println(String.format("monkey %d inspects its items", this.id));
  		while (this.itemList.size() > 0) {
			Item item = this.itemList.get(0);
//			System.out.println(
//					String.format("monkey %d inspects item with worrylevel %d", this.id, item.getWorryLevel()));
//			System.out.println(String.format("monkey %d applies operation on item with worrylevel %d ...", this.id,
//					item.getWorryLevel()));
 			this.op.apply(item);
			this.itemList.remove(item);
//			item.setWorryLevel(item.getWorryLevel().divide(new BigInteger("3")));
//			System.out.println(String.format("monkey %d removes item with worrylevel %d from its list ...", this.id,
//					item.getWorryLevel()));
 			boolean result = this.test.test(item.getWorryLevel());
			this.action.throwItem(result, item);
//			System.out.println(String.format("... and throws it away!"));
			this.inspectionCount++;
		}
	}

	@Override
	public String toString() {
		return "Monkey ["+ "inspectionCount=" + inspectionCount + "]";
	}

}
