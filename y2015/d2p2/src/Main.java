import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		System.out.println("CWD = " + System.getProperty("user.dir") + "\n\n");
		String filePath = "input.txt";
		try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
			String currLine;
			int[] dimensions = new int[3];
			int[] perimeters = new int[3];
			int step = 0;
			long total = 0;
			while ((currLine = inputFile.readLine()) != null) {
				step++;

				String[] dimensions_str = currLine.split("x"); // Extract dimensions from string
				dimensions[0] = Integer.parseInt(dimensions_str[0]); // Length
				dimensions[1] = Integer.parseInt(dimensions_str[1]); // Width
				dimensions[2] = Integer.parseInt(dimensions_str[2]); // Height

				perimeters[0] = 2*dimensions[0]+2*dimensions[1];
				perimeters[1] = 2*dimensions[1]+2*dimensions[2];
				perimeters[2] = 2*dimensions[2]+2*dimensions[0];
				Arrays.sort(perimeters); // First element will be the smallest perimeter

				total += perimeters[0] + (long)dimensions[0]*dimensions[1]*dimensions[2]; // Add smallest perimeter + volume (for the bow)

				System.out.println("Step: " + step + "; Total: " + total);
			}

			System.out.println("\nGrand total feet of ribbon needed: " + total);
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}
}
