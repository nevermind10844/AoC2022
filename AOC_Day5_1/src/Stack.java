import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Stack {
	private int id;
	private List<String> staple = null;
	private int index;
	
	public Stack() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getStaple() {
		return staple;
	}

	public void setStaple(List<String> staple) {
		this.staple = staple;
	}

	public int getIndex() {
		return index;
		
	}

	public void setIndex(int index) {
		this.index = index;
		
	}

	@Override
	public String toString() {
		return "Stack [id=" + id + ", staple=" + staple + ", index=" + index + "]";
	}
	
	public List<String> pop(int amount){
//		System.out.println("popping from " + this.id + " " + staple.size());
		List<String> stack = staple.subList(staple.size()-amount, staple.size());
		List<String> copy = new ArrayList<>(stack);
		this.staple = staple.subList(0, this.staple.size()-amount);
		return copy;
	}
	
	public void push(List<String> stack) {
//		System.out.println("pushing to " + this.id);
		this.staple.addAll(stack);
	}
}
