package avaj.weather;

import avaj.aircraft.*;
import avaj.weather.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public	class	Simulator {
	private static WeatherTower weatherTower;
	private static List<Flyable> flyables = new ArrayList<Flyable>();

	public static void main(String[] arg) throws InterruptedException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(arg[0]));
			String line = reader.readLine();
			if (line != null) {
				weatherTower = new WeatherTower();
				int simulations = Integer.parseInt(line.split(" ")[0]);
				if (simulations < 0) {
					System.out.println("Wrong simulation amount " + simulations);
					System.exit(1);
				}
				while ((line = reader.readLine()) != null) {
					Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
						Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
						Integer.parseInt(line.split(" ")[4]));
					if (flyable != null)
						flyables.add(flyable);
				}
				for (Flyable flyable : flyables) {
					flyable.registerTower(weatherTower);
				}
				for (int i = 1; i <= simulations; i++) {
					weatherTower.changeWeather();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("There is no file " + arg[0]);
		} catch (IOException e) {
			System.out.println("Error reading file " + arg[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please specify file");
		} catch (NullPointerException e) {
			System.out.println("Value is null");
		} catch (NumberFormatException e) {
			System.out.println("Incorrect numbers entered");
		} finally {
			WriteFile.getWriteFile().close();
		}
	}
}