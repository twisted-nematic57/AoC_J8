import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("CWD = " + System.getProperty("user.dir") + "\n\n");
		String filePath = "input.txt";
		try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
			String currLine;
			int step = 0, niceLines = 0, presentVowels = 0;
			boolean isNice = true, containsDouble = false, containsForbidden = false;
			char previousChar = '\0';

			char[] vowels = {'a','e','i','o','u'};
			String[] forbiddenStrings = {"ab","cd","pq","xy"};

			while ((currLine = inputFile.readLine()) != null) {
				step++;
				System.out.println("Line " + step + "; Nice lines = " + niceLines);

				presentVowels = 0;
				containsDouble = false;
				containsForbidden = false;
				isNice = true; // Assume compliance at the beginning, and remove nice status if the criteria is not satisfied down the line.

				for (char vowel : vowels) { // Check if it contains at least three vowels
					presentVowels += countOccurrences(currLine, vowel);
				}
				if (presentVowels < 3) isNice = false;

				previousChar = currLine.charAt(0); // Check if a char occurs twice in it at least once
				for (int i = 1; i < currLine.length(); i++) {
					if (previousChar == currLine.charAt(i)) {
						containsDouble = true;
						break;
					} else {
						previousChar = currLine.charAt(i);
					}
				}
				if(!containsDouble) isNice = false;

				for (String forbiddenString : forbiddenStrings) { // Check if it contains the forbidden strings
					if (currLine.contains(forbiddenString)) {
						containsForbidden = true;
					}
				}
				if (containsForbidden) isNice = false;

				if (isNice) niceLines++;
			}

			System.out.println("\nTotal # of nice lines: " + niceLines);
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}

	public static int countOccurrences(String str, char chr) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == chr) {
				count++;
			}
		}
		return count;
	}
}
