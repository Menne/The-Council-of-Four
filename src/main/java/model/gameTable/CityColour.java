package model.gameTable;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import model.bonus.ScoreBonus;

/**
 * Models a possible color of a city.  
 * @author Luca
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class CityColour {
	
	@XmlElement(name="name")
	private final String name;
	@XmlElement(name="colorBonus")
	private final ScoreBonus colorBonus;
	private boolean bonusAvailable;
	private final Set<City> citiesOfThisColour;
	
	/**
	 * Initially the bonus color is present and there are not any city of this color.
	 * The city's constructor will add the cities to the set.
	 * @param name of the color.
	 */
	public CityColour(String name, ScoreBonus colorBonus){
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



	public ScoreBonus getColorBonus() {
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