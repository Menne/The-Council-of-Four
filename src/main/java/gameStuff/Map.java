package gameStuff;

import java.util.Set;

public class Map {

	private final Set<City> cities;
	
	public Map(Set<City> cities){
		this.cities=cities;
	}

	public Set<City> getCities() {
		return cities;
	}
	
	
}
