import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		System.out.println("CWD = " + System.getProperty("user.dir") + "\n\n");
		String filePath = "input.txt";
		try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
			int currChar;
			// ...
			while ((currChar = inputFile.read()) != -1) {
				//...
			}
			
			//...
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}
}
