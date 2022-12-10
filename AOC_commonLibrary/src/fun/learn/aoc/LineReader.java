package fun.learn.aoc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LineReader {
	public static List<String> read(String filename) {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
        	return in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("I/O error for " + filename);
            return new ArrayList<String>();
        }
	}
}
