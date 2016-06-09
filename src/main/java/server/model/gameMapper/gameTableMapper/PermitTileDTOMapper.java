package server.model.gameMapper.gameTableMapper;

import modelDTO.gameTableDTO.PermitTileDTO;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;

public class PermitTileDTOMapper {
	
	private CityDTOMapper cityMapper;
	
	public PermitTileDTOMapper() {
		this.cityMapper=new CityDTOMapper();
	}
	
	public PermitTileDTO map(PermitTile realObject) {
		PermitTileDTO permitTileDTO=new PermitTileDTO();
		
		for(City city : realObject.getBuildableCities())
			permitTileDTO.getBuildablecities().add(this.cityMapper.map(city));
		permitTileDTO.setBonuses(realObject.getBonus());
		
		return permitTileDTO;		
	}

}
