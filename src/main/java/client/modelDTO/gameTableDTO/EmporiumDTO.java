package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;

public class EmporiumDTO implements ModelDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7152158224901304775L;
	private int playerNumber;
	private String playerName;
	
	public EmporiumDTO() {
		//non-arguments constructor
	}
	
	public EmporiumDTO(int playerNumber) {
		this.playerNumber=playerNumber;
	}
	

	public int getPlayerNumber() {
		return playerNumber;
	}
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public String getPlayerName() {
		return playerName;
	}
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

}
