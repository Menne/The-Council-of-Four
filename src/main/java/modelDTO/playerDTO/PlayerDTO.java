package modelDTO.playerDTO;

import java.util.ArrayList;
import java.util.List;

import players.Player;
import server.model.gameTable.PoliticsCard;
import modelDTO.ModelDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.PermitTileDTO;

public class PlayerDTO implements ModelDTO<Player>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7091136957135865278L;
	private String name;
	private int playerNumber;
	private int assistants;
	private int score;
	private int nobility;
	private int coins;
	private int emporiums;
	private List<CardColourDTO> hand;
	private List<PermitTileDTO> availablePermitTiles;
	
	public PlayerDTO(){
		
		this.hand=new ArrayList<>();
		this.availablePermitTiles=new ArrayList<>();
	}

	@Override
	public void map(Player realObject) {
		
		this.name=realObject.getName();
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getAssistants() {
		return assistants;
	}

	public List<CardColourDTO> getHand() {
		return hand;
	}

	public void setHand(List<CardColourDTO> hand) {
		this.hand = hand;
	}

	public void setAssistants(int assistants) {
		this.assistants = assistants;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNobility() {
		return nobility;
	}

	public void setNobility(int nobility) {
		this.nobility = nobility;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getEmporiums() {
		return emporiums;
	}

	public void setEmporiums(int emporiums) {
		this.emporiums = emporiums;
	}

	public List<PermitTileDTO> getAvailablePermitTiles() {
		return availablePermitTiles;
	}

	public void setAvailablePermitTiles(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles = availablePermitTiles;
	}

	@Override
	public String toString() {
		return "Player " + playerNumber + "\tname: "+ name + "\tassistants=" + assistants + "\tscore="
				+ score + "\tnobility=" + nobility + "\tcoins=" + coins + ", playersPermitTilesTurnedUp=" + availablePermitTiles +
				 ", emporiums=" + this.emporiums + "]\n\n";
	}



	
}
