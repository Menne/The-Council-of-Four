package modelDTO.gameTableDTO;

import java.util.HashSet;
import java.util.Set;

import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.Emporium;

public class CityDTO implements ModelDTO<City>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576160634819872933L;
	private String name;
	private boolean isKingPresent;
	private CityColourDTO colour;
	private Set<PlayerDTO> buildedEmporiums;
	private Set<Bonus> rewardToken;
	
	public CityDTO(){		
		this.buildedEmporiums=new HashSet<PlayerDTO>();
	}

	@Override
	public void map(City realObject) {
		this.name=realObject.getName();
		this.isKingPresent=realObject.getIsKingPresent();
		
		CityColourDTO cityColour=new CityColourDTO(); 
		cityColour.map(realObject.getColour());
		this.colour=cityColour;
		
		for(Emporium emporium : realObject.getCityEmporiums()){
			PlayerDTO playerDTO=new PlayerDTO();
			playerDTO.map(emporium.getEmporiumsPlayer());
			this.buildedEmporiums.add(playerDTO);
		}
		
		this.rewardToken=realObject.getRewardToken();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isKingPresent() {
		return isKingPresent;
	}

	public void setKingPresent(boolean isKingPresent) {
		this.isKingPresent = isKingPresent;
	}

	public CityColourDTO getColour() {
		return colour;
	}

	public void setColour(CityColourDTO colour) {
		this.colour = colour;
	}

	public Set<PlayerDTO> getBuildedEmporiums() {
		return buildedEmporiums;
	}

	public void setBuildedEmporiums(Set<PlayerDTO> buildedEmporiums) {
		this.buildedEmporiums = buildedEmporiums;
	}

	public Set<Bonus> getRewardToken() {
		return rewardToken;
	}

	public void setRewardToken(Set<Bonus> rewardToken) {
		this.rewardToken = rewardToken;
	}

	@Override
	public String toString() {
		return "CityDTO [name=" + name + ", isKingPresent=" + isKingPresent + ", colour=" + colour
				+ ", buildedEmporiums=" + buildedEmporiums + "]";
	}


	
	
}
