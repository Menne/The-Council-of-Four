package client.modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.modelDTO.ModelDTO;

public class CityDTO implements ModelDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576160634819872933L;
	private String name;
	private CityColourDTO colour;
	private Set<GenericPlayerDTO> buildedEmporiums;
	private RewardTokenDTO rewardToken;
	
	public CityDTO(){		
		this.buildedEmporiums=new HashSet<>();
	}
	
	public CityDTO(String name){
		this.name=name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public CityColourDTO getColour() {
		return colour;
	}

	public void setColour(CityColourDTO colour) {
		this.colour = colour;
	}

	public Set<GenericPlayerDTO> getBuildedEmporiums() {
		return buildedEmporiums;
	}

	public void setBuildedEmporiums(Set<GenericPlayerDTO> buildedEmporiums) {
		this.buildedEmporiums = buildedEmporiums;
	}

	

	public RewardTokenDTO getRewardToken() {
		return rewardToken;
	}


	public void setRewardToken(RewardTokenDTO rewardToken) {
		this.rewardToken = rewardToken;
	}


	@Override
	public String toString() {
		List<String> emporiums=new ArrayList<>();
		for(GenericPlayerDTO player : buildedEmporiums)
			emporiums.add(player.getName());
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
