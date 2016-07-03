package client.modelDTO.gameTableDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import server.model.gameTable.CouncilBalcony;

/**
 * This class provides all the informations about a region, but without logic
 * @author cg31
 *
 */
public class RegionDTO implements ModelDTO{

	private static final long serialVersionUID = 6668103687292375094L;
	private String name;
	private CouncillorDTO[] balcony;
	private Set<CityDTO> cities;
	private PermitTileDTO[] uncoveredPermitTiles;
	private BonusTileDTO regionBonus;
	
	/**
	 * Constructor of RegionDTO
	 */
	public RegionDTO() {
		this.balcony=new CouncillorDTO[CouncilBalcony.getNumberofcouncillors()]; 
		this.cities=new HashSet<>();		
		this.uncoveredPermitTiles=new PermitTileDTO[2];
	}
	
	/**
	 * @return the name of a generic player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of a generic player
	 * @param name is the player's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the region balcony of councillors DTO
	 */
	public CouncillorDTO[] getBalcony() {
		return this.balcony;
	}

	/**
	 * Sets the council balcony of the region DTO
	 * @param balcony is the council balcony DTO
	 */
	public void setBalcony(CouncillorDTO[] balcony) {
		this.balcony = balcony;
	}

	/**
	 * @return the cities DTO of the region
	 */
	public Set<CityDTO> getCities() {
		return this.cities;
	}

	/**
	 * Sets the cities DTO to a region DTO
	 * @param cities are the cities DTO
	 */
	public void setCities(Set<CityDTO> cities) {
		this.cities = cities;
	}

	/**
	 * @return the permit tiles DTO of the region
	 */
	public PermitTileDTO[] getUncoveredPermitTiles() {
		return this.uncoveredPermitTiles;
	}

	/**
	 * Sets the permit tiles DTO of the region DTO
	 * @param uncoveredPermitTiles are the uncovered permit tiles DTO of the region
	 */
	public void setUncoveredPermitTiles(PermitTileDTO[] uncoveredPermitTiles) {
		this.uncoveredPermitTiles = uncoveredPermitTiles;
	}

	/**
	 * @return the region bonus
	 */
	public BonusTileDTO getRegionBonus() {
		return regionBonus;
	}

	/**
	 * Sets the region bonus to the region DTO
	 * @param regionBonus is the region bonus
	 */
	public void setRegionBonus(BonusTileDTO regionBonus) {
		this.regionBonus = regionBonus;
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
		RegionDTO other = (RegionDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  "\n"+name + "\t" + Arrays.toString(balcony) + "\tTiles:" + Arrays.toString(uncoveredPermitTiles)+
				"\tBonus: "+regionBonus+"\n" + cities;
	}

	
	
}
