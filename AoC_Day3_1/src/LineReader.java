import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {
	public static List<String> read() {
		List<String> strings = new ArrayList<>();
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("ext/input.txt"));
			String line;

			while ((line = reader.readLine()) != null)
				strings.add(line);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strings;
	}
}
