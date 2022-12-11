package main;

import java.math.BigInteger;

public class Test {
	private int testValue;
	
	public Test(int testValue) {
		this.testValue = testValue;
	}
	
	public boolean test(BigInteger probe) {
		
		return probe.mod(BigInteger.valueOf(Long.valueOf(this.testValue))).equals(BigInteger.ZERO);
	}

	@Override
	public String toString() {
		return "Test [testValue=" + testValue + "]";
	}
	
}
