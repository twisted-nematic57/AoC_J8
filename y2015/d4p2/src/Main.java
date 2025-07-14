import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Main {
	public static void main(String[] args) {
		System.out.println("CWD = " + System.getProperty("user.dir") + "\n\n");
		String filePath = "input.txt";
		try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
			String input = inputFile.readLine(); // Single-line input
			int lowestSuffix = 0; // The lowest # suffix to the input that causes the first 6 digits of the MD5 hash to = 6 zeros in a row
			String md5HashString;

			for(int i = 0; i < Integer.MAX_VALUE; i++) {
				try {
					md5HashString = md5Hash(input + i).substring(0, 6);
					System.out.println("Trying input: " + input + i + " => " + md5HashString);
					if(md5HashString.equals("000000")) { // Check if the first 6 chars of the MD5 hash are "000000"
						lowestSuffix = i; // If yes, we're done!
						break;
					}
				} catch (NoSuchAlgorithmException e) {
					System.out.println("\n" + e.getMessage());
					System.out.println("\nFATAL ERROR: The algorithm could not be found. (???)"); // Let's pray exec never reaches this path.
				}
			}

			System.out.println("\nLowest suffix: " + lowestSuffix);
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}

	public static String md5Hash(String str) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(str.getBytes());
		return DatatypeConverter.printHexBinary(messageDigest.digest()); // Return hashed output
	}
}
