package client.modelDTO.gameTableDTO;

import java.util.HashSet;
import java.util.Set;

import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import server.model.bonuses.Bonus;

/**
 * This class provides all the informations about a permit tile, but without logic
 * @author cg31
 *
 */
public class PermitTileDTO implements ModelDTO, MarketableDTO {

	private static final long serialVersionUID = -2049414013378014856L;
	private Set<CityDTO> buildablecities;
	private Set<Bonus> bonuses;
	
	/**
	 * Constructor of PermitTileDTO
	 */
	public PermitTileDTO(){
		this.buildablecities=new HashSet<>();
		this.bonuses=new HashSet<>();
	}
	
	/**
	 * Constructor of PermitTileDTO
	 * @param buildablecities are the cities of the permit tile
	 * @param bonuses are the bonuses of the permit tile
	 */
	public PermitTileDTO(Set<CityDTO> buildablecities, Set<Bonus> bonuses){
		this.bonuses=bonuses;
		this.buildablecities=buildablecities;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
		result = prime * result + ((buildablecities == null) ? 0 : buildablecities.hashCode());
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
		PermitTileDTO other = (PermitTileDTO) obj;
		if (bonuses == null) {
			if (other.bonuses != null)
				return false;
		} else if (!bonuses.equals(other.bonuses))
			return false;
		if (buildablecities == null) {
			if (other.buildablecities != null)
				return false;
		} else if (!buildablecities.equals(other.buildablecities))
			return false;
		return true;
	}
	
	
}
