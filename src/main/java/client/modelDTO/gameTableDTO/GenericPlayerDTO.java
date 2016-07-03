
package client.modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about the other players in the game, but without logic
 * @author cg31
 *
 */
public class GenericPlayerDTO implements ModelDTO{

	private static final long serialVersionUID = -7091136957135865278L;
	private String name;
	private int playerNumber;
	private int assistants;
	private int score;
	private int nobility;
	private int coins;
	private int emporiums;
	private int hand;
	private int numberOfCoveredTiles;
	private List<PermitTileDTO> availablePermitTiles;
	private List<BonusTileDTO> playersFinalBonus;
	
	/**
	 * No-arguments constructor of GenericPlayerDTO
	 */
	public GenericPlayerDTO() {
		this.availablePermitTiles=new ArrayList<>();
		this.playersFinalBonus=new ArrayList<>();
	}
	
	/**
	 * Constructor of GenericPlayerDTO
	 * @param playerNumber is the player number
	 */
	public GenericPlayerDTO(int playerNumber) {
		this.playerNumber=playerNumber;
		this.availablePermitTiles=new ArrayList<>();
		this.playersFinalBonus=new ArrayList<>();
	}

	/**
	 * @return the number of covered permit tiles
	 */
	public int getNumberOfCoveredTiles() {
		return this.numberOfCoveredTiles;
	}

	/**
	 * Sets the number of uncovered permit tiles
	 * @param numberOfCoveredTiles is the number of covered permit tiles
	 */
	public void setNumberOfCoveredTiles(int numberOfCoveredTiles) {
		this.numberOfCoveredTiles = numberOfCoveredTiles;
	}

	/**
	 * @return the name of the generic player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the generic player
	 * @param name is the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number of the player
	 */
	public int getPlayerNumber() {
		return this.playerNumber;
	}

	/**
	 * Sets the number of the player
	 * @param playerNumber is the player number
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	/**
	 * @return the number of cards in the hand of a generic player
	 */
	public int getHand() {
		return this.hand;
	}

	/**
	 * Sets the number of cards in the hand of a generic player
	 * @param hand is the number of cards in the hand of a generic player
	 */
	public void setHand(int hand) {
		this.hand = hand;
	}

	/**
	 * @return the number of assistants of a generic player
	 */
	public int getAssistants() {
		return this.assistants;
	}
	
	/**
	 * Sets the number of assistants to a generic player
	 * @param assistants is the number of assistants
	 */
	public void setAssistants(int assistants) {
		this.assistants = assistants;
	}

	/**
	 * @return the number of assistants of a generic player
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Sets the score to a generic player
	 * @param score is player's score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the grade of nobility of a generic player
	 */
	public int getNobility() {
		return this.nobility;
	}

	/**
	 * Sets the grade of nobility to a generic player
	 * @param nobility is the grade of nobility
	 */
	public void setNobility(int nobility) {
		this.nobility = nobility;
	}

	/**
	 * @return the number of coins of a generic player
	 */
	public int getCoins() {
		return this.coins;
	}

	/**
	 * Sets the number of coins to a generic player
	 * @param coins is the number of coins
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}

	/**
	 * @return the number of emporiums of a generic player
	 */
	public int getEmporiums() {
		return emporiums;
	}

	/**
	 * Sets the number of emporiums to a generic player
	 * @param emporiums is the number of emporiums
	 */
	public void setEmporiums(int emporiums) {
		this.emporiums = emporiums;
	}

	/**
	 * @return the available permit tiles of a generic player
	 */
	public List<PermitTileDTO> getAvailablePermitTiles() {
		return this.availablePermitTiles;
	}

	/**
	 * Sets the available permit tiles of a generic player
	 * @param availablePermitTiles are the available permit tiles
	 */
	public void setAvailablePermitTiles(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles = availablePermitTiles;
	}

	/**
	 * @return the final bonuses a generic player
	 */
	public List<BonusTileDTO> getPlayersFinalBonus() {
		return this.playersFinalBonus;
	}

	/**
	 * Sets the final bonuses of a generic player
	 * @param playersFinalBonus are the finals bonus of a generic player
	 */
	public void setPlayersFinalBonus(List<BonusTileDTO> playersFinalBonus) {
		this.playersFinalBonus = playersFinalBonus;
	}


	@Override
	public String toString() {
		return "\nPlayer " + playerNumber + "\tname: "+ name + "\tassistants=" + assistants + "\tscore="
				+ score + "\tnobility=" + nobility + "\tcoins=" + coins + "\thand="+hand+"\tplayersPermitTilesTurnedUp=" + availablePermitTiles +
				 "\temporiums=" + this.emporiums + "\tfinalBonus= " + playersFinalBonus+"]";
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
		GenericPlayerDTO other = (GenericPlayerDTO) obj;
		if (playerNumber != other.playerNumber)
			return false;
		return true;
	}



	
}
