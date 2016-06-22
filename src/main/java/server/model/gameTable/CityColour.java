package server.model.gameTable;

import java.util.HashSet;
import java.util.Set;


/**
 * Models a possible color of a city.  
 * @author Luca
 *
 */

public class CityColour {
	
	private final String name;
	private final ColourBonusTile colorBonus;
	private boolean bonusAvailable;
	private final Set<City> citiesOfThisColour;
	
	/**
	 * Initially the bonus color is present and there are not any city of this color.
	 * The city's constructor will add the cities to the set.
	 * @param name of the color.
	 */
	public CityColour(String name, ColourBonusTile colorBonus){
		this.name=name;
		this.colorBonus=colorBonus;
		this.bonusAvailable=true;
		this.citiesOfThisColour=new HashSet<>();
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



	public ColourBonusTile getColorBonus() {
		return colorBonus;
	}



	public Set<City> getCitiesOfThisColour() {
		return citiesOfThisColour;
	}



	public void addCityOfThisColour(City c){
		this.citiesOfThisColour.add(c);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityColour other = (CityColour) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	

}