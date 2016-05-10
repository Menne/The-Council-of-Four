package gameStuff;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import bonus.Bonus;

/**
 * Models a city of the game
 * @author Luca
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class City {

	@XmlElement(name="name")
	private final String name;
	
	private Set<Bonus> rewardToken; //NON è PIù UN SET DI BONUS MA è UN OGGETTO REWARD TOKEN! COSTRUTTORE DEVE PRENDERNE UNO RANDOMICAMENTE!!
	
	@XmlElement(name="isKingPresent")
	private Boolean isKingPresent;
	private final RegionBoard region;
	
	@XmlElement(name="CityColour")
	private final CityColour colour;
	private final Set<Emporium> cityEmporiums;
	private final Set<City> nearCities;
	
	/**
	 * Create a new city. By default the set of near cities is empty,
	 * the king is not present, the array of emporiums is empty,
	 * the reward token is not present.
	 * Automatically adds the new city in the set of cities of the input color.
	 * Automatically adds the new city in the set of cities of the input region.
	 * @param name of the city.
	 * @param colour of the city.
	 * @param region of the city.
	 * @param numberOfPlayers of the game. 
	 * Useful to choose the size of the emporiums array.
	 */
	public City(String name, RegionBoard region, CityColour colour){
		this.name=name;
		this.isKingPresent=false;
		this.region=region;
		this.colour=colour;
		this.cityEmporiums=new HashSet<Emporium>();
		this.nearCities=new HashSet<City>();
		this.rewardToken=new HashSet<Bonus>();
		colour.addCityOfThisColour(this);
		region.addCityOfThisRegion(this);
	}

	
	
	public Set<Bonus> getRewardToken() {
		return rewardToken;
	}



	public void addBonus(Bonus bonus) {
		this.rewardToken.add(bonus);
	}



	public Boolean getIsKingPresent() {
		return isKingPresent;
	}



	public void setIsKingPresent(Boolean isKingPresent) {
		this.isKingPresent = isKingPresent;
	}



	public RegionBoard getRegion() {
		return region;
	}



	public String getName() {
		return name;
	}



	public CityColour getColour() {
		return colour;
	}



	public Set<Emporium> getCityEmporiums() {
		return cityEmporiums;
	}



	public Set<City> getNearCities() {
		return nearCities;
	}
	
	/**
	 * Adds c as a near city of the current city.
	 * @param c is the near city.
	 */
	public void addNearCity(City c){
		this.nearCities.add(c);
	}
	

	public void addEmporium(Emporium emporium) {
		this.cityEmporiums.add(emporium);
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
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
	
	


}