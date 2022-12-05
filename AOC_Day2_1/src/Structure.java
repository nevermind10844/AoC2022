import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

public class Structure {
	
	public static List<Entry<Thing, Thing>> translate(List<String> strings) {
		List<Entry<Thing, Thing>> strut = new ArrayList<>();
		
		for(String s : strings) {
			String[] split = s.trim().split(" ");
			Thing a = getThingBySign(split[0]);
			strut.add(new AbstractMap.SimpleEntry<>(a, getThingByIndicator(a, split[1])));
		}
		
		return strut;
	}
	
	public static Thing getThingBySign(String sign) {
		return Arrays.asList(Thing.values()).stream().filter(thing -> sign.equals(thing.getA())).findFirst().get();
	}
	
	public static Thing getThingByIndicator(Thing a, String indicator) {
		switch(indicator) {
			case "X":
				return Thing.losesTo(a);
			case "Z":
				return Thing.winsAgainst(a);
			default:
				return Thing.drawsWith(a);
		}
	}
	
	public static int compareResult(Thing a, Thing b) {
		System.out.println(String.format("comparing %s vs %s", a.getText(), b.getText()));
		if(a.equals(b))
			return 3;
		
		if(a.equals(Thing.ROCK))
			return b.equals(Thing.PAPER) ? 6 : 0;
		else if(a.equals(Thing.PAPER))
			return b.equals(Thing.SCISSORS) ? 6 : 0;
		else
			return b.equals(Thing.ROCK) ? 6 : 0;
	}
}
