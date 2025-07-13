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
			int[] areas = new int[3];
			int step = 0;
			long total = 0;
			while ((currLine = inputFile.readLine()) != null) {
				step++;

				String[] dimensions_str = currLine.split("x"); // Extract dimensions from string
				dimensions[0] = Integer.parseInt(dimensions_str[0]); // Length
				dimensions[1] = Integer.parseInt(dimensions_str[1]); // Width
				dimensions[2] = Integer.parseInt(dimensions_str[2]); // Height

				areas[0] = dimensions[0]*dimensions[1];
				areas[1] = dimensions[1]*dimensions[2];
				areas[2] = dimensions[2]*dimensions[0];
				Arrays.sort(areas); // The "slack" (area of smallest side)

				total += (2L*dimensions[0]*dimensions[1] + // -----------------------
									2L*dimensions[1]*dimensions[2] + // Surface area calculation (2*l*w + 2*w*h + 2*h*l)
									2L*dimensions[2]*dimensions[0] + // -----------------------
									areas[0]); // Gotta add the slack too!

				System.out.println("Step: " + step + "; Total: " + total);
			}

			System.out.println("\nGrand total wrapping paper needed: " + total);
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}
}
