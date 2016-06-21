 package server.model.player;

import java.util.ArrayList;
import java.util.List;

import server.model.gameTable.Assistant;
import server.model.gameTable.BonusTile;
import server.model.gameTable.Emporium;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;

/** Player class contains all of the informations about a player
	 * 
	 * @author Emanuele, Luca
	 *
	 */

public class Player {

	private int playerNumber;
	private String name;
	private final List<Assistant> assistants;
	private int score;
	private int nobility;
	private int coins;
	private final List<PoliticsCard> hand;
	private final List<PermitTile> playersPermitTilesTurnedUp;
	private final List<PermitTile> playersPermitTilesTurnedDown;
	private final List<Emporium> emporiums;
	private final List<BonusTile> playersFinalBonus;
	
	
	/**
	 * Sets nobility and score at 0. 
	 * Adds to the hand of the player the initial politics cards
	 * @param playerNumber the sequential number of the player in the game
	 * @param name of the player
	 * @param assistants number of initial assistants
	 * @param coins number of initial coins
	 */
	public Player(String name) {
		this.assistants=new ArrayList<>();
		this.hand=new ArrayList<>();
		this.playersPermitTilesTurnedDown=new ArrayList<>();
		this.playersPermitTilesTurnedUp=new ArrayList<>();
		this.emporiums=new ArrayList<>();
		this.playersFinalBonus=new ArrayList<>();
		this.name=name;
	}
	
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumberOfAssistants() {
		return this.assistants.size();
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNobility() {
		return nobility;
	}
	
	public List<PoliticsCard> getHand() {
		return hand;
	}


	public int getCoins() {
		return coins;
	}
	

	public List<Emporium> getRemainigEmporiums() {
		return emporiums;
	}
	
	public List<PermitTile> getPlayersPermitTilesTurnedUp() {
		return playersPermitTilesTurnedUp;
	}

	public List<PermitTile> getPlayersPermitTilesTurnedDown() {
		return playersPermitTilesTurnedDown;
	}

	public List<BonusTile> getPlayersFinalBonus() {
		return playersFinalBonus;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setNobility(int nobility) {
		this.nobility = nobility;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	/**
	 * increment player's Score of an "increment" calling the method incrementScore in the class Score
	 * @param increment
	 * @param score
	 */
	public void incrementScore(int increment) {
		this.score+=increment;
	}
	
	/**
	 * increments player's assistants number of an "increment"
	 * @param increment
	 */
	public void incrementAssistants(int increment) {
		for(int i=0; i<increment; i++)
			this.assistants.add(new Assistant());
	}
	/**
	 * decrements number of player's assistances if it has more assistances than the decrement.
	 * it returns false if player has too few assistants!
	 * @param decrement
	 */
	public boolean decrementAssistants(int decrement) {
		if(this.assistants.size()<decrement)
			return false;
		for(int i=0; i<decrement; i++)
			this.assistants.remove(0);
		return true;
	}
	
	/**
	 * Increments player's nobility position of an "increment"
	 * If the increment is too big the new player's nobility will be the max nobility
	 * @param increment of nobility
	 * @param maxNobility the superior limit for the player's nobility
	 */
	public void incrementNobility(int increment, int maxNobility) {
		if(this.nobility+increment<=maxNobility)
			this.nobility+=increment;
		else
			this.nobility=maxNobility;
	}

	/**
	 * increments player's coins of a "increment"
	 * @param increment
	 */
	public void incrementCoins(int increment) {
		this.coins+=increment;
	}
	/**
	 * decrements player's coins if players has more coins than decrement.
	 * it returns false if player has too few coins!
	 * @param decrement
	 */
	public boolean decrementCoins(int decrement) {
		if(coins>=decrement){
			this.coins-=decrement;
			return true;
			}
		else
			return false;
	}
	/**
	 * adds a bonus to playersFinalBonus list
	 * @param bonus
	 */
	public void addFinalBonus(BonusTile bonus){
		playersFinalBonus.add(bonus);
	}
	
	/**
	 * adds one "card" to the player's deck
	 * @param card
	 */
	public void addCardToHand(PoliticsCard card){
		this.hand.add(card);
	}
	
	/**
	 * removes one card "card" from the player's deck
	 * returns TRUE if player's deck contained the card; 
	 * returns FALSE if player's deck didn't contain the card.
	 * @param card
	 */
	public boolean removeCardFromHand(PoliticsCard card){
		return this.hand.remove(card);
	}
	
	public void addTile(PermitTile tile){
		this.playersPermitTilesTurnedUp.add(tile);
	}
	
	/**
	 * turn a tile from up to down.
	 * into his "playersPermitTilesTurnedUp
	 * @param tile to cover
	 * @return TRUE if tile is an uncovered permit tile of the player. FALSE otherwise.
	 */
	public boolean turnTileToDown(PermitTile tile){
		if(!this.playersPermitTilesTurnedUp.contains(tile))
			return false;
		playersPermitTilesTurnedDown.add(tile);
		return this.playersPermitTilesTurnedUp.remove(tile);
	}
	
	/**
	 * remove an emporium
	 * @return the removed emporium
	 */
	public Emporium removeEmporium(){		
		return this.emporiums.remove(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerNumber;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerNumber != other.playerNumber)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Player " + playerNumber + "\tassistants=" + assistants.size() + "\tscore="
				+ score + "\tnobility=" + nobility + "\tcoins=" + coins + "\n hand=" + hand
				+ ", playersPermitTilesTurnedUp=" + playersPermitTilesTurnedUp + ", playersPermitTilesTurnedDown="
				+ playersPermitTilesTurnedDown + ", emporiums=" + this.emporiums.size() + ", finalBuons "+playersFinalBonus+"]\n\n";
	}
	
	
}