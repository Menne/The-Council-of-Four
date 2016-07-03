package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about an emporium, but without logic
 * @author cg31
 *
 */
public class EmporiumDTO implements ModelDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7152158224901304775L;
	private int playerNumber;
	private String playerName;
	
	/**
	 * Constructor with no arguments
	 */
	public EmporiumDTO() {
		//non-arguments constructor
	}
	
	/**
	 * Constructor with player Number
	 * @param playerNumber the player number of player who owns the emporium
	 */
	public EmporiumDTO(int playerNumber) {
		this.playerNumber=playerNumber;
	}
	

	/**
	 * @return the player number of player who owns the emporium
	 */
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	/**
	 * Sets the player number of player who owns the emporium
	 * @param playerNumber
	 */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	/**
	 * @return the player name of player who owns the emporium
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Sets the player number of player who owns the emporium
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
		EmporiumDTO other = (EmporiumDTO) obj;
		if (playerNumber != other.playerNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmporiumDTO [playerName=" + playerName + "]";
	}	
	
}
