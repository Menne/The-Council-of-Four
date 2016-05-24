package client.ModelDTO;

import java.util.ArrayList;
import java.util.List;

import model.gameTable.PermitTile;
import model.gameTable.PoliticsCard;
import players.Player;

public class PlayerDTO implements ModelDTO<Player>{

	private int playerNumber;
	private int assistants;
	private int score;
	private int nobility;
	private int coins;
	private int emporiums;
	private List<CardColourDTO> hand;
	private List<PermitTile> availablePermitTiles;
	
	public PlayerDTO(){
		
		this.hand=new ArrayList<CardColourDTO>();
		this.availablePermitTiles=new ArrayList<PermitTile>();
	}

	@Override
	public void map(Player realObject) {
		this.playerNumber=realObject.getPlayerNumber();
		this.assistants=realObject.getNumberOfAssistants();
		this.score=realObject.getScore();
		this.nobility=realObject.getNobility();
		this.coins=realObject.getCoins();
		this.emporiums=realObject.getRemainigEmporiums().size();
		for(PoliticsCard card : realObject.getHand()){
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(card.getColour());
			this.hand.add(cardColourDTO);
		}
		
	}
	
	@Override
	public String toString() {
		return "PlayerDTO [playerNumber=" + playerNumber + ", assistants=" + assistants + ", score=" + score
				+ ", nobility=" + nobility + ", coins=" + coins + ", emporiums=" + emporiums + ", hand=" + hand
				+ ", availablePermitTiles=" + availablePermitTiles + "]";
	}



	
}
