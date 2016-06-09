package server.model.gameMapper.gameTableMapper;

import modelDTO.gameTableDTO.RegionDTO;
import server.model.gameTable.City;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.RegionBoard;

public class RegionDTOMapper {
	
	private CardColourDTOMapper cardColourMapper;
	private CityDTOMapper cityMapper;
	private PermitTileDTOMapper permitTileMapper;
	
	public RegionDTOMapper() {
		this.cardColourMapper=new CardColourDTOMapper();
		this.cityMapper=new CityDTOMapper();
		this.permitTileMapper=new PermitTileDTOMapper();
	}
	
	public RegionDTO map(RegionBoard realObject) {
		RegionDTO regionDTO=new RegionDTO();
		
		regionDTO.setName(realObject.getName());
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			regionDTO.getBalcony()[i]=this.cardColourMapper.map
					(realObject.getRegionBalcony().getCouncillors()[i].getColour());
		for (City city : realObject.getRegionCities())
			regionDTO.getCities().add(this.cityMapper.map(city));
		for (int i=0; i<2; i++)
			regionDTO.getUncoveredPermitTiles()[i]=this.permitTileMapper.map
					(realObject.getUncoveredPermitTiles()[i]);
		
		return regionDTO;
	}

}
