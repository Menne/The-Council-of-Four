package client.ModelDTO;

import java.util.HashSet;
import java.util.Set;

import model.gameTable.CityColour;

public class CityDTO {

	private String name;
	private boolean isKingPresent;
	private CityColour colour;
	private Set<PlayerDTO> buildedEmporiums;
	
	public CityDTO(){
		this.buildedEmporiums=new HashSet<PlayerDTO>();
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

	public CityColour getColour() {
		return colour;
	}

	public void setColour(CityColour colour) {
		this.colour = colour;
	}

	public Set<PlayerDTO> getBuildedEmporiums() {
		return buildedEmporiums;
	}

	public void setBuildedEmporiums(Set<PlayerDTO> buildedEmporiums) {
		this.buildedEmporiums = buildedEmporiums;
	}
	
}
