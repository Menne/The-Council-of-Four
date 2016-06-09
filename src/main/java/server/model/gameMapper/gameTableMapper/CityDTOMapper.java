package server.model.gameMapper.gameTableMapper;

import modelDTO.gameTableDTO.CityDTO;
import server.model.gameTable.City;
import server.model.gameTable.Emporium;

public class CityDTOMapper {
	
	private CityColourDTOMapper cityColourMapper;
	private GenericPlayerDTOMapper genericPlayerMapper;

	public CityDTOMapper() {
		this.cityColourMapper=new CityColourDTOMapper();
		this.genericPlayerMapper=new GenericPlayerDTOMapper();
	}
	
	public CityDTO map(City realObject) {
		
		CityDTO cityDTO=new CityDTO();
		
		cityDTO.setName(realObject.getName());
		cityDTO.setColour(this.cityColourMapper.map(realObject.getColour()));
		for (Emporium emporium : realObject.getCityEmporiums())
			cityDTO.getBuildedEmporiums().add
					(this.genericPlayerMapper.map(emporium.getEmporiumsPlayer()));
		cityDTO.setRewardToken(realObject.getRewardToken());
		
		return cityDTO;
	}

}
