package client.modelDTO.gameTableDTO;

import java.util.HashSet;
import java.util.Set;

import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import server.model.bonus.Bonus;

public class PermitTileDTO implements ModelDTO, MarketableDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2049414013378014856L;
	private Set<CityDTO> buildablecities;
	private Set<Bonus> bonuses;
	
	public PermitTileDTO(){
		this.buildablecities=new HashSet<>();
		this.bonuses=new HashSet<>();
	}


	public Set<CityDTO> getBuildablecities() {
		return this.buildablecities;
	}

	public void setBuildablecities(Set<CityDTO> buildablecities) {
		this.buildablecities=buildablecities;
	}

	public Set<Bonus> getBonuses() {
		return this.bonuses;
	}

	public void setBonuses(Set<Bonus> bonuses) {
		this.bonuses=bonuses;
	}

	@Override
	public String toString() {
		Set<String> cities=new HashSet<>();
		for(CityDTO cityDTO : buildablecities)
			cities.add(cityDTO.getName());
		return cities + "\t" + bonuses;
	}
	
	
}
