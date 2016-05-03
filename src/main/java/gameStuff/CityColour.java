package gameStuff;

import java.util.HashSet;
import java.util.Set;

import bonus.Bonus;

/**
 * Models a possible color of a city.  
 * @author Luca
 *
 */

public class CityColour {

	private final String name;
	private final Bonus colorBonus;
	private boolean bonusAvailable;
	private final Set<City> citiesOfThisColour;
	
	/**
	 * Initially the bonus color is present and there are not any city of this color.
	 * The city's constructor will add the cities to the set.
	 * @param name of the color.
	 */
	public CityColour(String name, Bonus colorBonus){
		this.name=name;
		this.colorBonus=colorBonus;
		this.bonusAvailable=true;
		this.citiesOfThisColour=new HashSet<City>();
	}
	


	public boolean isBonusAvailable() {
		return bonusAvailable;
	}



	public void notBonusAvailable() {
		this.bonusAvailable = false;
	}



	public String getName() {
		return name;
	}



	public Bonus getColorBonus() {
		return colorBonus;
	}



	public Set<City> getCitiesOfThisColour() {
		return citiesOfThisColour;
	}



	public void addCityOfThisColour(City c){
		this.citiesOfThisColour.add(c);
	}


}