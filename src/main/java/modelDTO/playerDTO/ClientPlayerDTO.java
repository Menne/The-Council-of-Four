package modelDTO.playerDTO;

import java.util.ArrayList;
import java.util.List;

import modelDTO.ModelDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import players.Player;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;

public class ClientPlayerDTO implements ModelDTO<Player>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1059151897076991279L;
	private List<CardColourDTO> hand;
	private List<PermitTileDTO> coveredPermitTiles;
	private List<PermitTileDTO> availablePermitTiles;
	private int assistants;
	
	public ClientPlayerDTO(){
		this.hand=new ArrayList<CardColourDTO>();
		this.coveredPermitTiles=new ArrayList<PermitTileDTO>();
		this.availablePermitTiles=new ArrayList<PermitTileDTO>();
	}


	@Override
	public void map(Player realObject) {
		
		for (PoliticsCard card : realObject.getHand()) {
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(card.getColour());
			this.hand.add(cardColourDTO);
		}
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedDown()) {
			PermitTileDTO permitTileDTO=new PermitTileDTO();
			permitTileDTO.map(permitTile);
			this.coveredPermitTiles.add(permitTileDTO);
		}
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedUp()) {
			PermitTileDTO permitTileDTO=new PermitTileDTO();
			permitTileDTO.map(permitTile);
			this.availablePermitTiles.add(permitTileDTO);
		}
			
	}
	
	
	public List<CardColourDTO> getHand() {
		return hand;
	}

	public void setHand(List<CardColourDTO> hand) {
		this.hand = hand;
	}

	public List<PermitTileDTO> getCoveredPermitTiles() {
		return coveredPermitTiles;
	}

	public void setCoveredPermitTiles(List<PermitTileDTO> coveredPermitTiles) {
		this.coveredPermitTiles = coveredPermitTiles;
	}
	
	public List<PermitTileDTO> getAvailablePermitTiles() {
		return availablePermitTiles;
	}

	public void setAvailablePermitTiles(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles = availablePermitTiles;
	}
	
	public void setAssistants(int assistants) {
		this.assistants = assistants;
	}
	
	public int getAssistants() {
		return assistants;
	}

}
