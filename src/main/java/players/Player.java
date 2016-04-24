package players;

	/** Player class constains all of the informations abaut a player
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
	
	public Player() {
		this.playerNumber=playerNumber;
		this.name=name;
		this.assistants=assistants;
		this.score=score;
		this.nobility=nobility;
		this.coins=coins;
		this.isPlayerTurn=isPlayerTurn;
	}

	public getNumber() {
		return number;
	}
	
	public getName() {
		return name;
	}
	
	public getAssistants() {
		return assistants;
	}
	
	public getScore() {
		return score;
	}
	
	public getNobility() {
		return nobility;
	}
	
	public getCoins() {
		return coins;
	}
	
	public void incrementAssistants(int increment) {
		this.assistsants+=increment;
	}
	
	public void incrementNobility(int increment) {
		this.nobility+=increment;
	}
	
	public void incrementCoins(int increment) {
		this.coins+=increment;
	}
}