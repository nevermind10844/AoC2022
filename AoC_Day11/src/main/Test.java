package main;

import java.math.BigInteger;
import java.util.List;

public class Test {
	private BigInteger testValue;
	
	public Test(BigInteger testValue) {
		this.testValue = testValue;
	}
	
	public boolean test(BigInteger probe) {
		if(this.testValue.equals(new BigInteger("2"))) {
			String s = probe.toString();
//			System.out.println(s);
			int i = Integer.valueOf(s.substring(s.length()-1));
//			System.out.println(i);
			boolean result = i % 2 == 0;
//			System.out.println(result);
			return result;
		} else if(this.testValue.equals(new BigInteger("5"))){
			String s = probe.toString();
//			System.out.println(s);
			int i = Integer.valueOf(s.substring(s.length()-1));
//			System.out.println(i);
			boolean result = i % 5 == 0;
//			System.out.println(result);
			return result;
		} else if(this.testValue.equals(new BigInteger("3"))) {
			BigInteger num = new BigInteger("0");
			List<Integer> intList = probe.toString().chars().boxed().toList();
			for (Integer integer : intList) {
				int i = integer - 48;
				num.add(new BigInteger(String.valueOf(i)));
			}
			return num.remainder(this.testValue).signum() == 0;
		} else {
			return probe.remainder(this.testValue).signum() == 0;
		}
	}

	@Override
	public String toString() {
		return "Test [testValue=" + testValue + "]";
	}
	
}
