package client.ModelDTO;

import java.util.HashSet;
import java.util.Set;

import model.gameTable.CardColour;
import model.gameTable.PermitTile;

public class RegionDTO {

	private String name;
	private CardColour[] balcony;
	private Set<CityDTO> cities;
	private PermitTile[] uncoveredPermitTiles;
	
	public RegionDTO(){
		this.balcony=new CardColour[4];
		this.cities=new HashSet<CityDTO>();
		this.uncoveredPermitTiles=new PermitTile[2];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CardColour[] getBalcony() {
		return balcony;
	}

	public void setBalcony(CardColour[] balcony) {
		this.balcony = balcony;
	}

	public Set<CityDTO> getCities() {
		return cities;
	}

	public void setCities(Set<CityDTO> cities) {
		this.cities = cities;
	}

	public PermitTile[] getUncoveredPermitTiles() {
		return uncoveredPermitTiles;
	}

	public void setUncoveredPermitTiles(PermitTile[] uncoveredPermitTiles) {
		this.uncoveredPermitTiles = uncoveredPermitTiles;
	}
	
}
