package client.ModelDTO;

import java.util.HashSet;
import java.util.Set;

import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;

public class PermitTileDTO implements ModelDTO<PermitTile>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2049414013378014856L;
	private Set<CityDTO> buildablecities;
	private Set<Bonus> bonuses;
	
	public PermitTileDTO(){
		this.buildablecities=new HashSet<CityDTO>();
		this.bonuses=new HashSet<Bonus>();
	}

	@Override
	public void map(PermitTile realObject) {
		for(City city : realObject.getBuildableCities()){
			CityDTO cityDTO=new CityDTO();
			cityDTO.map(city);
			this.buildablecities.add(cityDTO);
		}
		this.bonuses=realObject.getBonus();		
	}

	public Set<CityDTO> getBuildablecities() {
		return buildablecities;
	}

	public void setBuildablecities(Set<CityDTO> buildablecities) {
		this.buildablecities = buildablecities;
	}

	public Set<Bonus> getBonuses() {
		return bonuses;
	}

	public void setBonuses(Set<Bonus> bonuses) {
		this.bonuses = bonuses;
	}

	@Override
	public String toString() {
		Set<String> cities=new HashSet<String>();
		for(CityDTO cityDTO : buildablecities)
			cities.add(cityDTO.getName());
		return cities + "\t" + bonuses;
	}
	
	
}
