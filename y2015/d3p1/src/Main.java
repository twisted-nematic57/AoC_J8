import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class House {
	int[] coords = new int[2];
	int receivedPresents = 1;
}

public class Main {
	public static void main(String[] args) {
		System.out.println("CWD = " + System.getProperty("user.dir") + "\n\n");
		String filePath = "input.txt";
		try (BufferedReader inputFile = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
			int currChar;
			int step = 0;
			int[] currCoords = new int[]{0,0};

			List<House> houses = new ArrayList<>();
			houses.add(new House());
			houses.get(0).coords = currCoords.clone();
			System.out.println("DBG: Current coords: " + Arrays.toString(currCoords)); // DBG

			while ((currChar = inputFile.read()) != -1) {
				System.out.println((char)currChar); // DBG
				step++;

				switch(currChar) {
					case '>': // Right (East)
						currCoords[0]++;
						System.out.println("DBG: Current coords: " + Arrays.toString(currCoords)); // DBG

						List<House> houseSearch = houses.stream() // See if there are any houses with the coords we're on now
								.filter(h -> Arrays.equals(h.coords, currCoords))
								.collect(Collectors.toList());

						System.out.println("DBG: houseSearch.size = " + houseSearch.size());

						if(houseSearch.isEmpty()) { // If no house exists with current coords, add a new house.
							houses.add(new House());
							houses.get(houses.size() - 1).coords = currCoords.clone();
						} else { // If a house exists at this coordinate pair, record another present delivered to this one.
							houseSearch.get(0).receivedPresents++;
							System.out.println("DBG: houseSearch.get(0).coords = " + Arrays.toString(houseSearch.get(0).coords));
							System.out.println("DBG: houseSearch.get(0).receivedPresents = " + houseSearch.get(0).receivedPresents);
						}

						if(houseSearch.size() > 1) {
							System.out.println("\n\nTHERE IS A SERIOUS PROBLEM\n\n"); // DBG
						}

						break;
				}
				//System.out.println("DBG: Current coords: " + Arrays.toString(currCoords)); // DBG
			}

			//...
			System.out.println("\n\nDBG: Total unique houses visited: " + houses.size()); // DBG
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}
}
