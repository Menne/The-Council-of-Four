package server.model.gameMapper.playerMapper;

import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.model.gameMapper.gameTableMapper.CardColourDTOMapper;
import server.model.gameMapper.gameTableMapper.PermitTileDTOMapper;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;

public class ClientPlayerDTOMapper {
	
	private CardColourDTOMapper cardColourMapper;
	private PermitTileDTOMapper permitTileMapper;

	public ClientPlayerDTOMapper() {
		this.cardColourMapper=new CardColourDTOMapper();
		this.permitTileMapper=new PermitTileDTOMapper();
	}
	
	public ClientPlayerDTO map(Player realObject) {
		
		ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
		
		clientPlayerDTO.setName(realObject.getName());
		clientPlayerDTO.setPlayerNumber(realObject.getPlayerNumber());
		for (PoliticsCard card : realObject.getHand())
			clientPlayerDTO.getHand().add(this.cardColourMapper.map(card.getColour()));
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedDown())
			clientPlayerDTO.getCoveredPermitTiles().add(this.permitTileMapper.map(permitTile));
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedUp())
			clientPlayerDTO.getCoveredPermitTiles().add(this.permitTileMapper.map(permitTile));
		clientPlayerDTO.setAssistants(realObject.getNumberOfAssistants());
		
		return clientPlayerDTO;	
	}

}
