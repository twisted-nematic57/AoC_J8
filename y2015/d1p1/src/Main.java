import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		System.out.println("CWD = " + System.getProperty("user.dir") + "\n\n");
		String filePath = "input.txt";
		try (BufferedReader f = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
			int ch;
			int floor = 0, step = 0;
			while ((ch = f.read()) != -1) {
				step++;
				if(ch == '(') {
					floor++;
				} else {
					floor--;
				}
				System.out.println("Step = " + step + "; Current floor = " + String.format("% d", floor));
			}
			
			System.out.println("\nFinal floor = " + floor);
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}
}
