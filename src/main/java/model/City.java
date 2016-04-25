package model;

import java.util.Set;

/**
 * Class City identified by its name
 * @author andreapasquali
 *
 */
public class City {

	private final String name;
	private final RewardToken rewardToken;
	private Boolean isKingPresent;
	private final CityColour colour;
	private final RegionBoard region;
	private Set<Emporium> citysEmporiums;
	private final Set<City> nearCities;
	
	public City(String name, RewardToken rewardToken, Boolean isKingPresent, CityColour colour, RegionBoard
			region, Set<Emporium> citysEmporium, Set<City> nearCities){
		this.name=name;
		this.rewardToken=rewardToken;
		this.isKingPresent=isKingPresent;
		this.colour=colour;
		this.region=region;
		this.citysEmporiums=citysEmporium;
		this.nearCities=nearCities;
	}
	
	public Boolean getIsKingPresent() {
		return isKingPresent;
	}


	public CityColour getColour() {
		return colour;
	}


	public RegionBoard getRegion() {
		return region;
	}


	public Set<Emporium> getCitysEmporiums() {
		return citysEmporiums;
	}


	public void setIsKingPresent(Boolean isKingPresent) {
		this.isKingPresent = isKingPresent;
	}


	public RewardToken getRewardToken() {
		return rewardToken;
	}

	
	public String getName() {
		return name;
	}

	public Set<City> getNearCities(){
		return nearCities;
	}
	
	/**
	 * Djiksta algorithm TODO
	 * @param City
	 */
	public int distance(int City) {
		// TODO - implement City.distance
		throw new UnsupportedOperationException();
	}


}