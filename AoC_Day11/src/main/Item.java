package main;

import java.math.BigInteger;

public class Item {
	private String uid;
	private BigInteger worryLevel;

	public Item(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public BigInteger getWorryLevel() {
		return worryLevel;
	}

	public void setWorryLevel(BigInteger worryLevel) {
		this.worryLevel = worryLevel;
	}

	@Override
	public String toString() {
		return "Item [uid=" + uid + ", worryLevel=" + worryLevel + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item other = (Item) obj;
			if (other.uid.equals(this.uid))
				return true;
		}
		return false;
	}
	
}
