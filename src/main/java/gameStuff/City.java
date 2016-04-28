package gameStuff;

import java.util.Set;


import gameStuff.RegionBoard;
import gameStuff.RewardToken;
import players.Player;

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
	private static Set<Emporium> citysEmporiums;
	private final Set<City> nearCities;
	
	public City(String name, RewardToken rewardToken, Boolean isKingPresent, CityColour colour, RegionBoard
			region, Set<Emporium> citysEmporium, Set<City> nearCities){
		this.name=name;
		this.rewardToken=rewardToken;
		this.isKingPresent=isKingPresent;
		this.colour=colour;
		this.region=region;
		City.citysEmporiums=citysEmporium;
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
	 * adds an emporium e to the cities set if the player hasn't any emporium on that city;
	 * else it returns FALSE
	 * @param e
	 */
	public boolean addEmporium(Emporium e){
		if(citysEmporiums.contains(e)){
		citysEmporiums.add(e);
		return true;}
		else
			return false;
	}
	
	/**
	 * it returns true if city contains an emporium of Player player
	 * @param player
	 * @return
	 */
	public static boolean containsEmporium(Player player){
		if(citysEmporiums.contains(Emporium.getEmporiumsPlayer=player)==true)
			return true;
		else 
			return false;
	}
	
	/**
	 * Djikstra algorithm TODO
	 * @param City
	 */
	public int distance(int City) {
		// TODO - implement City.distance
		throw new UnsupportedOperationException();
	}


}