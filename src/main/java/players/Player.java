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
	
	public Player(int playerNumber, String name, int assistants, Score score, int nobility, int coins, boolean isPlayerTurn) {
		this.playerNumber=playerNumber;
		this.name=name;
		this.assistants=assistants;
		this.score=score;
		this.nobility=nobility;
		this.coins=coins;
		this.isPlayerTurn=isPlayerTurn;
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

	
	
	public void incrementAssistants(int increment) {
		this.assistants+=increment;
	}
	
	public void incrementNobility(int increment) {
		this.nobility+=increment;
	}
	
	public void incrementCoins(int increment) {
		this.coins+=increment;
	}
}