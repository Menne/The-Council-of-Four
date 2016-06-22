package client.modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.notifies.ClientViewNotify;
import observerPattern.Observable;
import server.model.bonus.Bonus;


public class GameTableDTO extends Observable<ClientViewNotify> implements ModelDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250245390720798602L;
	private ArrayList<RegionDTO> clientRegions;
	private CouncillorDTO[] clientKingBalcony;
	private List<CouncillorDTO> clientCouncillorReserve;
	private ArrayList<Set<Bonus>> clientNobilityTrack;
	private ArrayList<GenericPlayerDTO> players;
	private String currentPlayer;
	private String king;
	private BonusTileDTO nextKingRewardTile;
	private Set<BonusTileDTO> colourBonuses;
	
	public GameTableDTO() {
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CouncillorDTO[4];
		this.clientCouncillorReserve=new ArrayList<CouncillorDTO>();
		this.clientNobilityTrack=new ArrayList<Set<Bonus>>();
		this.players=new ArrayList<GenericPlayerDTO>();
		this.colourBonuses=new HashSet<>();
	}

	
	public List<RegionDTO> getClientRegions() {
		return clientRegions;
	}

	public void setClientRegions(List<RegionDTO> clientRegions) {
		this.clientRegions = (ArrayList<RegionDTO>) clientRegions;
	}

	

	public CouncillorDTO[] getClientKingBalcony() {
		return clientKingBalcony;
	}


	public void setClientKingBalcony(CouncillorDTO[] clientKingBalcony) {
		this.clientKingBalcony = clientKingBalcony;
	}


	public List<CouncillorDTO> getClientCouncillorReserve() {
		return this.clientCouncillorReserve;
	}

	public void setClientCouncillorReserve(List<CouncillorDTO> clientCouncillorReserve) {
		this.clientCouncillorReserve=clientCouncillorReserve;
	}

	public List<Set<Bonus>> getClientNobilityTrack() {
		return clientNobilityTrack;
	}

	public void setClientNobilityTrack(List<Set<Bonus>> clientNobilityTrack) {
		this.clientNobilityTrack = (ArrayList<Set<Bonus>>) clientNobilityTrack;
	}

	public List<GenericPlayerDTO> getClientPlayers() {
		return players;
	}

	public void setClientPlayers(List<GenericPlayerDTO> clientPlayers) {
		this.players = (ArrayList<GenericPlayerDTO>) clientPlayers;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getKing() {
		return king;
	}

	public void setKing(String king) {
		this.king = king;
	}

	public BonusTileDTO getNextKingRewardTile() {
		return nextKingRewardTile;
	}


	public void setNextKingRewardTile(BonusTileDTO nextKingRewardTile) {
		this.nextKingRewardTile = nextKingRewardTile;
	}


	public Set<BonusTileDTO> getColourBonuses() {
		return colourBonuses;
	}


	public void setColourBonuses(Set<BonusTileDTO> colourBonuses) {
		this.colourBonuses = colourBonuses;
	}


	@Override
	public String toString() {
		return "\nUpdated game table:\nPlayers:\n" + players + "\nNow is plaiyng:\t" + currentPlayer + "\n" +
				 clientRegions + "\nKingCity: "+ king+" \nKing's balcony:" + Arrays.toString(clientKingBalcony) + "\nCouncillors riserve"+
				 clientCouncillorReserve + "\nNobilityTrack: " + clientNobilityTrack
				 +"\nNextKingRewardTile: "+nextKingRewardTile+"\nColourBonuses: "+colourBonuses;
	}
	
	

	
}
