package gameStuff;

import java.util.HashSet;
import java.util.Set;

/**
 * Models the a city of the game
 * @author Luca
 *
 */
public class City {

	private final String name;
	private RewardToken rewardToken;
	private Boolean isKingPresent;
	private final CityColour colour;
	private final Emporium[] citysEmporiums;
	private final Set<City> nearCities;
	
	/**
	 * Create a new city. By default the set of near cities is empty,
	 * the king is not present, the array of emporiums is empty,
	 * the reward token is not present.
	 * Automatically adds the new city in the set of cities with the input color.
	 * @param name of the city.
	 * @param colour of the city
	 * @param numberOfPlayers of the game. 
	 * Usefull to choose the size of the emporiums array.
	 */
	public City(String name, CityColour colour, int numberOfPlayers){
		this.name=name;
		this.isKingPresent=false;
		this.colour=colour;
		this.citysEmporiums=new Emporium[numberOfPlayers];
		this.nearCities=new HashSet<City>();
		colour.addCityOfThisColour(this);
	}

	
	
	public RewardToken getRewardToken() {
		return rewardToken;
	}



	public void setRewardToken(RewardToken rewardToken) {
		this.rewardToken = rewardToken;
	}



	public Boolean getIsKingPresent() {
		return isKingPresent;
	}



	public void setIsKingPresent(Boolean isKingPresent) {
		this.isKingPresent = isKingPresent;
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
	
	
	public void addNearCity(City c){
		this.nearCities.add(c);
	}



	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
	
	


}