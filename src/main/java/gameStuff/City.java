package gameStuff;

import java.util.HashSet;
import java.util.Set;
import bonus.Bonus;

/**
 * Models the a city of the game
 * @author Luca
 *
 */
public class City {

	private final String name;
	private final Set<Bonus> rewardToken;
	private Boolean isKingPresent;
	private final RegionBoard region;
	private final CityColour colour;
	private final Emporium[] citysEmporiums;
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
	 * Usefull to choose the size of the emporiums array.
	 */
	public City(String name, RegionBoard region, CityColour colour, int numberOfPlayers){
		this.name=name;
		this.isKingPresent=false;
		this.region=region;
		this.colour=colour;
		this.citysEmporiums=new Emporium[numberOfPlayers];
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



	public Emporium[] getCitysEmporiums() {
		return citysEmporiums;
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