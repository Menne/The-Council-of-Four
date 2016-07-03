package client.modelDTO.playerDTO;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.BonusTileDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;

/**
 * This class provides all the informations about the player, but without logic
 * @author cg31
 *
 */
public class ClientPlayerDTO implements ModelDTO{
	
	private static final long serialVersionUID = 1059151897076991279L;
	private String name;
	private int playerNumber;
	private List<PoliticsCardDTO> hand;
	private List<PermitTileDTO> coveredPermitTiles;
	private List<PermitTileDTO> availablePermitTiles;
	private List<BonusTileDTO> finalBonuses;
	private List<AssistantDTO> assistants;
	private int coins;
	private int score;
	private int nobility;
	private int emporiums;
	
	/**
	 * Constructor of ClientPlayerDTO
	 */
	public ClientPlayerDTO() {
		this.hand=new ArrayList<>();
		this.coveredPermitTiles=new ArrayList<>();
		this.availablePermitTiles=new ArrayList<>();
		this.finalBonuses=new ArrayList<>();
	}

	/**
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the player
	 * @param name is the name of the player
	 */
	public void setName(String name) {
		this.name=name;
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
		this.playerNumber=playerNumber;
	}	

	/**
	 * @return the politics cards in the hand of a player DTO
	 */
	public List<PoliticsCardDTO> getHand() {
		return this.hand;
	}

	/**
	 * Sets the politics cards in the hand of a player DTO
	 * @param hand are the politics cards in the hand of a player
	 */
	public void setHand(List<PoliticsCardDTO> hand) {
		this.hand=hand;
	}

	/**
	 * Sets the covered permit tiles of a generic player
	 * @param coveredPermitTiles are the covered permit tiles
	 */
	public List<PermitTileDTO> getCoveredPermitTiles() {
		return this.coveredPermitTiles;
	}

	/**
	 * Sets the covered permit tiles of a generic player
	 * @param coveredPermitTiles are the covered permit tiles
	 */
	public void setCoveredPermitTiles(List<PermitTileDTO> coveredPermitTiles) {
		this.coveredPermitTiles=coveredPermitTiles;
	}
	
	/**
	 * @return the available permit tiles of a player
	 */
	public List<PermitTileDTO> getAvailablePermitTiles() {
		return this.availablePermitTiles;
	}

	/**
	 * Sets the available permit tiles of a player
	 * @param availablePermitTiles are the available permit tiles
	 */
	public void setAvailablePermitTiles(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles=availablePermitTiles;
	}
	
	/**
	 * Sets the assistants to a player DTO
	 * @param assistants is the list of assistants of the player DTO
	 */
	public void setAssistants(List<AssistantDTO> assistants) {
		this.assistants=assistants;
	}
	
	/**
	 * @return the assistants of a player DTO
	 */
	public List<AssistantDTO> getAssistants() {
		return this.assistants;
	}

	/**
	 * @return the coins of a player
	 */
	public int getCoins() {
		return this.coins;
	}

	/**
	 * Sets the coins of a player
	 * @param coins is player's coins
	 */
	public void setCoins(int coins) {
		this.coins=coins;
	}

	/**
	 * @return the score of a player
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Sets the score of a player
	 * @param score is player's score
	 */
	public void setScore(int score) {
		this.score=score;
	}

	/**
	 * @return the grade of nobility of a player
	 */
	public int getNobility() {
		return this.nobility;
	}

	/**
	 * Sets the grade of nobility of a player
	 * @param nobility is the grade of nobility
	 */
	public void setNobility(int nobility) {
		this.nobility=nobility;
	}

	/**
	 * @return the number of emporiums of a player
	 */
	public int getEmporiums() {
		return emporiums;
	}

	/**
	 * Sets the number of emporiums of a player
	 * @param emporiums is the number of emporiums
	 */
	public void setEmporiums(int emporiums) {
		this.emporiums = emporiums;
	}
	
	/**
	 * @return the final bonuses of a player
	 */
	public List<BonusTileDTO> getFinalBonuses() {
		return this.finalBonuses;
	}

	/**
	 * Sets the final bonuses of a player
	 * @param availablePermitTiles the final bonuses
	 */
	public void setFinalBonuses(List<BonusTileDTO> finalBonuses) {
		this.finalBonuses = finalBonuses;
	}


	@Override
	public String toString() {
		return "\n" + this.getName() +", Here is your current status: [score=" + score + ", hand=" + hand + ", coveredPermitTiles=" + coveredPermitTiles
				+ ", availablePermitTiles=" + availablePermitTiles + ", assistants=" + assistants + ", coins=" + coins + ", nobility=" + nobility + 
				", finalBonuses: "+finalBonuses +"]";
	}

}
