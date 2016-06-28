package server.model.gameTable;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Models a city of the game
 * @author Luca
 *
 */

public class City {

	private final String name;
	private final RewardToken rewardToken;
	private final RegionBoard region;
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
	public City(String name, RegionBoard region, CityColour colour, List<RewardToken> rewardTokenList){
		this.name=name;
		this.region=region;
		this.colour=colour;
		this.cityEmporiums=new HashSet<>();
		this.nearCities=new HashSet<>();
		if(!"KingColour".equals(colour.getName())){
			Random random=new Random();
			this.rewardToken=rewardTokenList.remove(random.nextInt(rewardTokenList.size()));
		}
		else
			this.rewardToken=new RewardToken(new HashSet<>());
		colour.addCityOfThisColour(this);
		region.addCityOfThisRegion(this);
	}

	
	
	

	public RewardToken getRewardToken() {
		return rewardToken;
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
	
	/**
	 * Adds emporium to the set of Emporium of the city
	 * @param emporium is the emporium to add
	 */
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
		return name+"\t"+cityEmporiums+"\t"+rewardToken+"\n";
	}
	
	


}