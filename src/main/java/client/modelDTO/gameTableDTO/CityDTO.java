package client.modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about a city, but without logic
 * @author cg31
 *
 */
public class CityDTO implements ModelDTO{

	private static final long serialVersionUID = 3576160634819872933L;
	private String name;
	private CityColourDTO colour;
	private Set<EmporiumDTO> buildedEmporiums;
	private RewardTokenDTO rewardToken;
	
	/**
	 * No-arguments constructor of CityDTO
	 */
	public CityDTO(){		
		this.buildedEmporiums=new HashSet<>();
	}
	
	/**
	 * Constructor of CityDTO
	 * @param name is the name of the city
	 */
	public CityDTO(String name){
		this.name=name;
	}
	
	/**
	 * @return the name of the city
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name to the city
	 * @param name is the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name of the colour of the city
	 */
	public CityColourDTO getColour() {
		return this.colour;
	}

	/**
	 * Sets the name of the colour to the city
	 * @param colour is the colour to set
	 */
	public void setColour(CityColourDTO colour) {
		this.colour = colour;
	}

	/**
	 * @return the emporiums built in the city
	 */
	public Set<EmporiumDTO> getBuildedEmporiums() {
		return buildedEmporiums;
	}

	/**
	 * Sets the emporiums in the city
	 * @param buildedEmporiums are the emporiums built in the city
	 */
	public void setBuildedEmporiums(Set<EmporiumDTO> buildedEmporiums) {
		this.buildedEmporiums = buildedEmporiums;
	}

	/**
	 * @return the reward token of the city
	 */
	public RewardTokenDTO getRewardToken() {
		return this.rewardToken;
	}

	/**
	 * Sets a token to the city DTO
	 * @param rewardToken is the token to set to the city
	 */
	public void setRewardToken(RewardTokenDTO rewardToken) {
		this.rewardToken = rewardToken;
	}


	@Override
	public String toString() {
		List<String> emporiums=new ArrayList<>();
		for(EmporiumDTO player : buildedEmporiums)
			emporiums.add(player.getPlayerName());
		return name+"\t"+emporiums+"\t"+rewardToken+"\n";
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
		CityDTO other = (CityDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	
	
}
