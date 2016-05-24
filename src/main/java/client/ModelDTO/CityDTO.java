package client.ModelDTO;

import java.util.HashSet;
import java.util.Set;

import model.gameTable.City;
import model.gameTable.Emporium;

public class CityDTO implements ModelDTO<City>{

	private String name;
	private boolean isKingPresent;
	private CityColourDTO colour;
	private Set<PlayerDTO> buildedEmporiums;
	
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
	}
	
	
	@Override
	public String toString() {
		return "CityDTO [name=" + name + ", isKingPresent=" + isKingPresent + ", colour=" + colour
				+ ", buildedEmporiums=" + buildedEmporiums + "]";
	}


	
	
}
