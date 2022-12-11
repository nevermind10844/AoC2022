package main;

import java.math.BigInteger;

public class Operation {
	private OperationType opType;
	private BigInteger opValue;
	
	public Operation() {
		
	}
	
	public Operation(Operation.OperationType opType, BigInteger opValue) {
		this.opType = opType;
		this.opValue = opValue;
	}

	public OperationType getOpType() {
		return opType;
	}

	public void setOpType(OperationType opType) {
		this.opType = opType;
	}

	public BigInteger getOpValue() {
		return opValue;
	}

	public void setOpValue(BigInteger opValue) {
		this.opValue = opValue;
	}
	
	public void apply(Item item) {
		switch (this.opType) {
		case ADD:
			item.setWorryLevel(item.getWorryLevel().add(this.opValue));
//			System.out.println(String.format("worrylevel is increased by %d to %d", this.opValue, item.getWorryLevel()));
			break;
		case SUB:
			item.setWorryLevel(item.getWorryLevel().subtract(this.opValue));
			break;
		case DIV:
			item.setWorryLevel(item.getWorryLevel().divide(this.opValue));
			break;
		case MULT:
 			item.setWorryLevel(item.getWorryLevel().multiply(this.opValue));
// 			System.out.println(String.format("worrylevel is multplied by %d to %d", this.opValue, item.getWorryLevel()));
 			break;
		case POW:
			BigInteger itemWorryLevel = item.getWorryLevel();
			item.setWorryLevel(itemWorryLevel.multiply(itemWorryLevel));
//			System.out.println(String.format("worrylevel is sqaured by %d to %d", itemWorryLevel, item.getWorryLevel()));
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		return "Operation [opType=" + opType + ", opValue=" + opValue + "]";
	}

	public enum OperationType{
		MULT, ADD, SUB, DIV, POW
	}
}
