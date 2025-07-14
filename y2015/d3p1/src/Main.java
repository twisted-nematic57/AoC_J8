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
	public static void processDelivery(List<House> houseList, int[] coords) {
		List<House> houseSearch = houseList.stream() // See if there are any houses with the coords we're on now
				.filter(h -> Arrays.equals(h.coords, coords))
				.collect(Collectors.toList());

		if(houseSearch.isEmpty()) { // If no house exists with current coords, add a new house.
			houseList.add(new House());
			houseList.get(houseList.size() - 1).coords = coords.clone();
		} else { // If a house exists at this coordinate pair, record another present delivered to this one.
			houseSearch.get(0).receivedPresents++;
		}
	}

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
			System.out.println("Starting coords: " + Arrays.toString(currCoords));

			while ((currChar = inputFile.read()) != -1) {
				step++;

				switch(currChar) {
					case '>': // Right (East)
						currCoords[0]++;
						processDelivery(houses, currCoords);
						break;
					case '<': // Left (West)
						currCoords[0]--;
						processDelivery(houses, currCoords);
						break;
					case '^': // Up (North)
						currCoords[1]++;
						processDelivery(houses, currCoords);
						break;
					case 'v': // Down (South)
						currCoords[1]--;
						processDelivery(houses, currCoords);
						break;
				}
				System.out.println("Step: " + step + "; Current coords: " + Arrays.toString(currCoords));
			}

			System.out.println("Total houses that received at least one present: " + houses.size());
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage());
			System.out.println("\nFATAL ERROR: The input file was not found.");
		}
	}
}
