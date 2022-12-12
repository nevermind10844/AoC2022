package main;

import java.math.BigInteger;

public class Test {
	private BigInteger testValue;

	public Test(BigInteger testValue) {
		this.testValue = testValue;
	}

	public int getTestValue() {
		return this.testValue.intValue();
	}

	public boolean test(BigInteger probe) {
		return probe.remainder(this.testValue).signum() == 0;
	}

	@Override
	public String toString() {
		return "Test [testValue=" + testValue + "]";
	}

}
