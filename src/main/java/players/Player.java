package players;

import java.util.List;
import java.util.Set;

import gameStuff.PermitTile;
import gameStuff.PoliticsCard;

/** Player class constains all of the informations about a player
	 * 
	 * @author Emanuele
	 *
	 */

public class Player {

	private final int playerNumber;
	private final String name;
	private int assistants;
	private Score score;
	private int nobility;
	private int coins;
	private boolean isPlayerTurn;
	private List<PoliticsCard> deck;
	private Set<PermitTile> playersPermitTilesTurnedUp;
	private Set<PermitTile> playersPermitTilesTurnedDown;
	private int remainingEmporiums 
	
	public Player(int playerNumber, String name, int assistants, Score score, int nobility, int coins, boolean isPlayerTurn,
			List<PoliticsCard> deck, Set<PermitTile> playersPermitTilesTurnedUp, Set<PermitTile> playersPermitTilesTurnedDown) {
		this.playerNumber=playerNumber;
		this.name=name;
		this.assistants=assistants;
		this.score=score;
		this.nobility=nobility;
		this.coins=coins;
		this.isPlayerTurn=isPlayerTurn;
		this.deck=deck;
		this.playersPermitTilesTurnedDown=playersPermitTilesTurnedDown;
		this.playersPermitTilesTurnedUp=playersPermitTilesTurnedUp;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAssistants() {
		return assistants;
	}
	
	public Score getScore() {
		return score;
	}
	
	public int getNobility() {
		return nobility;
	}
	
	public int getCoins() {
		return coins;
	}

	/**
	 * increment player's Score of an "increment" calling the method incrementScore in the class Score
	 * @param increment
	 * @param score
	 */
	public void incrementScore(int increment, Score score) {
		score.incrementScore(increment);
	}
	
	/**
	 * increments player's assistants number of an "increment"
	 * @param increment
	 */
	public void incrementAssistants(int increment) {
		this.assistants+=increment;
	}
	/**
	 * decrements number of player's assistances if it has more assistances than the decrement.
	 * it returns false if player has too few assistants!
	 * @param decrement
	 */
	public boolean decrementAssistants(int decrement) {
		if(assistants>=decrement){
			this.assistants-=decrement;
			return true;}
		else
			return false;
	}
	
	/**
	 * increments player's nobility position of an "increment"
	 * @param increment
	 */
	public void incrementNobility(int increment) {
		this.nobility+=increment;
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
	 * adds one "card" to the player's deck
	 * @param card
	 */
	public void addCardToDeck(PoliticsCard card){
		deck.add(card);
	}
	
	/**
	 * removes one card "card" from the player's deck
	 * returns TRUE if player's deck contained the card; 
	 * returns FALSE if player's deck didn't contain the card.
	 * @param card
	 */
	public boolean removeCardFromDeck(PoliticsCard card){
		return deck.remove(card);
	}
	
	public void addTile(PermitTile tile){
		playersPermitTilesTurnedUp.add(tile);
	}
	
	/**
	 * turn a tile from up to down; it return TRUE if it is possible, FALSE if player hasn't the "tile"
	 * into his "playersPermitTilesTurnedUp
	 * @param tile
	 * @return
	 */
	public boolean turnTileToDown(PermitTile tile){
		playersPermitTilesTurnedDown.add(tile);
		return playersPermitTilesTurnedUp.remove(tile);
	}
}