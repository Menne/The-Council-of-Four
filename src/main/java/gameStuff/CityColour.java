package gameStuff;

import java.util.HashSet;
import java.util.Set;

/**
 * Models a possible color of a city.  
 * @author Luca
 *
 */

public class CityColour {

	private final String name;
	private boolean presenceBonusColour;
	private final Set<City> citiesOfThisColour;
	
	/**
	 * Initially the bonus color is present and there are not any city of this color.
	 * The city's constructor will add the cities to the set.
	 * @param name of the color.
	 */
	public CityColour(String name){
		this.name=name;
		this.presenceBonusColour=true;
		this.citiesOfThisColour=new HashSet<City>();
	}
	
	public boolean isPresenceBonusColour() {
		return presenceBonusColour;
	}

	public void setPresenceBonusColour(boolean presenceBonusColour) {
		this.presenceBonusColour = presenceBonusColour;
	}

	public String getName() {
		return name;
	}

	public Set<City> getCitiesOfThisColour() {
		return citiesOfThisColour;
	}

	public void addCityOfThisColour(City c){
		this.citiesOfThisColour.add(c);
	}


}