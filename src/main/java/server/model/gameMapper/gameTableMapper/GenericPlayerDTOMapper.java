package server.model.gameMapper.gameTableMapper;

import modelDTO.gameTableDTO.GenericPlayerDTO;
import players.Player;
import server.model.gameTable.PermitTile;

public class GenericPlayerDTOMapper {
	
	private PermitTileDTOMapper permitTileMapper;
	
	public GenericPlayerDTOMapper() {
		this.permitTileMapper=new PermitTileDTOMapper();
	}
	
	public GenericPlayerDTO map(Player realObject) {
		GenericPlayerDTO genericPlayerDTO=new GenericPlayerDTO();
		
		genericPlayerDTO.setName(realObject.getName());
		genericPlayerDTO.setPlayerNumber(realObject.getPlayerNumber());
		genericPlayerDTO.setAssistants(realObject.getNumberOfAssistants());
		genericPlayerDTO.setScore(realObject.getScore());
		genericPlayerDTO.setNobility(realObject.getNobility());
		genericPlayerDTO.setCoins(realObject.getCoins());
		genericPlayerDTO.setEmporiums(realObject.getRemainigEmporiums().size());
		genericPlayerDTO.setHand(realObject.getHand().size());
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedUp())
			genericPlayerDTO.getAvailablePermitTiles().add
					(this.permitTileMapper.map(permitTile));
		
		return genericPlayerDTO;
	}

}
