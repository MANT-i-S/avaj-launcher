package avaj.aircraft;

import	avaj.weather.*;

public class jetPlane extends Aircraft implements Flyable {
	private WeatherTower _weatherTower;

	jetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);		
	}

	public	void	updateConditions() {
		String weather = this._weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "SUN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
				this.coordinates.setHeight(this.coordinates.getHeight() + 2);
				if (this.coordinates.getHeight() > 100)
					this.coordinates.setHeight(100);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Oh! The sun is so bright!");
				break;
			case "RAIN":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Seems like it's raining!");
				break;
			case "FOG":
				this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Stop smoking in here! I dont see anything!");
				break;
			case "SNOW":
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Oh its a snow! Lets play snowballs xD");
				break;
			default:
			WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + "): Im completely lost...");
			break;
		}
		if (this.coordinates.getHeight() <= 0) {
			WriteFile.getWriteFile().writetofile("JetPlane#" + this.name + "(" + this.id + ") landing.");
			WriteFile.getWriteFile().writetofile("Tower  says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
		}
	}

	public	void	registerTower(WeatherTower weatherTower) {
		this._weatherTower = weatherTower;
		WriteFile.getWriteFile().writetofile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		this._weatherTower.register(this);
	}
	
}