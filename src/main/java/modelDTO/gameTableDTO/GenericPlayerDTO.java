package modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.List;

import players.Player;
import server.model.gameTable.PermitTile;
import modelDTO.ModelDTO;

public class GenericPlayerDTO implements ModelDTO<Player>{

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
	private int hand;
	private List<PermitTileDTO> availablePermitTiles;
	
	public GenericPlayerDTO(){
		this.availablePermitTiles=new ArrayList<PermitTileDTO>();
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
		this.hand=realObject.getHand().size();
		
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedUp()) {
			PermitTileDTO permitTileDTO=new PermitTileDTO();
			permitTileDTO.map(permitTile);
			this.availablePermitTiles.add(permitTileDTO);
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

	public int getHand() {
		return hand;
	}

	public void setHand(int hand) {
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
		return "\nPlayer " + playerNumber + "\tname: "+ name + "\tassistants=" + assistants + "\tscore="
				+ score + "\tnobility=" + nobility + "\tcoins=" + coins + "\thand="+hand+"\tplayersPermitTilesTurnedUp=" + availablePermitTiles +
				 "\temporiums=" + this.emporiums + "]";
	}



	
}
