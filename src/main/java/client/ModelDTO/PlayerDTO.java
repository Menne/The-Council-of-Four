package client.ModelDTO;

import java.util.ArrayList;
import java.util.List;

import model.gameTable.CardColour;
import model.gameTable.PermitTile;

public class PlayerDTO {

	private int playerNumber;
	private int assistants;
	private int score;
	private int nobility;
	private int coins;
	private int emporiums;
	private List<CardColour> hand;
	private List<PermitTile> availablePermitTiles;
	
	public PlayerDTO(){
		this.hand=new ArrayList<CardColour>();
		this.availablePermitTiles=new ArrayList<PermitTile>();
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

	public List<CardColour> getHand() {
		return hand;
	}

	public void setHand(List<CardColour> hand) {
		this.hand = hand;
	}

	public List<PermitTile> getAvailablePermitTiles() {
		return availablePermitTiles;
	}

	public void setAvailablePermitTiles(List<PermitTile> availablePermitTiles) {
		this.availablePermitTiles = availablePermitTiles;
	}
	
	
}
