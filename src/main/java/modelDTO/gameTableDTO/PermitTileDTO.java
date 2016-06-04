package modelDTO.gameTableDTO;

import java.util.HashSet;
import java.util.Set;

import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;
import modelDTO.ModelDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.marketDTO.MarketableDTO;

public class PermitTileDTO implements ModelDTO<PermitTile>, MarketableDTO {

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
