package client.modelDTO.playerDTO;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;

public class ClientPlayerDTO implements ModelDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1059151897076991279L;
	private String name;
	private int playerNumber;
	private List<PoliticsCardDTO> hand;
	private List<PermitTileDTO> coveredPermitTiles;
	private List<PermitTileDTO> availablePermitTiles;
	private List<AssistantDTO> assistants;
	
	public ClientPlayerDTO() {
		this.hand=new ArrayList<PoliticsCardDTO>();
		this.coveredPermitTiles=new ArrayList<PermitTileDTO>();
		this.availablePermitTiles=new ArrayList<PermitTileDTO>();
	}
	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public int getPlayerNumber() {
		return this.playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber=playerNumber;
	}	

	public List<PoliticsCardDTO> getHand() {
		return this.hand;
	}

	public void setHand(List<PoliticsCardDTO> hand) {
		this.hand=hand;
	}

	public List<PermitTileDTO> getCoveredPermitTiles() {
		return this.coveredPermitTiles;
	}

	public void setCoveredPermitTiles(List<PermitTileDTO> coveredPermitTiles) {
		this.coveredPermitTiles = coveredPermitTiles;
	}
	
	public List<PermitTileDTO> getAvailablePermitTiles() {
		return this.availablePermitTiles;
	}

	public void setAvailablePermitTiles(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles = availablePermitTiles;
	}
	
	public void setAssistants(List<AssistantDTO> assistants) {
		this.assistants = assistants;
	}
	
	public List<AssistantDTO> getAssistants() {
		return this.assistants;
	}


	@Override
	public String toString() {
		return "\n" + this.getName() +", Here is your current status: [hand=" + hand + ", coveredPermitTiles=" + coveredPermitTiles
				+ ", availablePermitTiles=" + availablePermitTiles + ", assistants=" + assistants + "]";
	}

}
