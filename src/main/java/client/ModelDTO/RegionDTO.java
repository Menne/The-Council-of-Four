package client.ModelDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.gameTable.City;
import model.gameTable.CouncilBalcony;
import model.gameTable.PermitTile;
import model.gameTable.RegionBoard;

public class RegionDTO implements ModelDTO<RegionBoard>{

	private String name;
	private CardColourDTO[] balcony;
	private Set<CityDTO> cities;
	private PermitTile[] uncoveredPermitTiles;
	
	public RegionDTO(){
		
		this.balcony=new CardColourDTO[4]; 
		this.cities=new HashSet<CityDTO>();		
		this.uncoveredPermitTiles=new PermitTile[2];
	}

	@Override
	public void map(RegionBoard realObject) {
		this.name=realObject.getName();
		
		for(int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++){
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(realObject.getRegionBalcony().getCouncillors()[i].getColour());
			this.balcony[i]=cardColourDTO;
		}
		
		for(City city : realObject.getRegionCities()){
			CityDTO cityDTO=new CityDTO();
			cityDTO.map(city);
			this.cities.add(cityDTO);
		}
	}
	
	
	@Override
	public String toString() {
		return "RegionDTO [name=" + name + ", balcony=" + Arrays.toString(balcony) + ", cities=" + cities
				+ ", uncoveredPermitTiles=" + Arrays.toString(uncoveredPermitTiles) + "]";
	}

	
	
}
