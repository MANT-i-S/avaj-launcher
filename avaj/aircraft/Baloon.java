package avaj.aircraft;

import	avaj.weather.*;
import	avaj.aircraft.*;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;
	
	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
				this.coordinates.setHeight(this.coordinates.getHeight() + 4);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setLongitude(100);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): Sun bright like a diamonds!");
				break;
			case "RAIN":
				this.coordinates.setHeight(this.coordinates.getHeight() - 5);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): Its so wet in here.");
				break;
			case "FOG":
				this.coordinates.setHeight(this.coordinates.getHeight() - 2);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): Its a fog!");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 15);
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): The winter is coming.");
				WriteFile.getWriteFile().writetofile("Tower  says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
				break;
			default:
				WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + "): Im lost too...");
				break;
		}
		if (this.coordinates.getHeight() <= 0){
			WriteFile.getWriteFile().writetofile("Baloon#" + this.name + "(" + this.id + ") landing.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		WriteFile.getWriteFile().writetofile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		weatherTower.register(this);
	}
}