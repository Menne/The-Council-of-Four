package server.model.gameMapper.gameTableMapper;

import modelDTO.gameTableDTO.CityColourDTO;
import server.model.gameTable.CityColour;

public class CityColourDTOMapper {
	
	public CityColourDTO map(CityColour realObject) {
		CityColourDTO cityColourDTO=new CityColourDTO();
		cityColourDTO.setName(realObject.getName());
		return cityColourDTO;
	}

}
