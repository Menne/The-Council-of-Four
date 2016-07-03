package client.modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.clientNotifies.ClientViewNotify;
import client.modelDTO.ModelDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import observerPattern.Observable;
import server.model.bonuses.Bonus;

/**
 * This class provides all the informations about the game table, but without logic
 * @author cg31
 *
 */
public class GameTableDTO extends Observable<ClientViewNotify> implements ModelDTO {
	
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
	private int mapNumber;
	
	/**
	 * Constructor of GameTableDTO
	 */
	public GameTableDTO() {
		this.clientRegions=new ArrayList<>();
		this.clientKingBalcony=new CouncillorDTO[4];
		this.clientCouncillorReserve=new ArrayList<>();
		this.clientNobilityTrack=new ArrayList<>();
		this.players=new ArrayList<>();
		this.colourBonuses=new HashSet<>();
	}

	
	public List<RegionDTO> getClientRegions() {
		return this.clientRegions;
	}

	public void setClientRegions(List<RegionDTO> clientRegions) {
		this.clientRegions = (ArrayList<RegionDTO>) clientRegions;
	}

	public CouncillorDTO[] getClientKingBalcony() {
		return this.clientKingBalcony;
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
		return this.clientNobilityTrack;
	}

	public void setClientNobilityTrack(List<Set<Bonus>> clientNobilityTrack) {
		this.clientNobilityTrack = (ArrayList<Set<Bonus>>) clientNobilityTrack;
	}

	public List<GenericPlayerDTO> getClientPlayers() {
		return this.players;
	}

	public void setClientPlayers(List<GenericPlayerDTO> clientPlayers) {
		this.players = (ArrayList<GenericPlayerDTO>) clientPlayers;
	}

	public String getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String getKing() {
		return this.king;
	}

	public void setKing(String king) {
		this.king = king;
	}

	public BonusTileDTO getNextKingRewardTile() {
		return this.nextKingRewardTile;
	}

	public void setNextKingRewardTile(BonusTileDTO nextKingRewardTile) {
		this.nextKingRewardTile = nextKingRewardTile;
	}

	public Set<BonusTileDTO> getColourBonuses() {
		return this.colourBonuses;
	}


	public void setColourBonuses(Set<BonusTileDTO> colourBonuses) {
		this.colourBonuses = colourBonuses;
	}
	
	
	public int getMapNumber() {
		return this.mapNumber;
	}


	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}


	@Override
	public String toString() {
		return "\nUpdated game table:\nPlayers:\n" + players + "\nNow is plaiyng:\t" + currentPlayer + "\n" +
				 clientRegions + "\nKingCity: "+ king+" \nKing's balcony:" + Arrays.toString(clientKingBalcony) + "\nCouncillors riserve"+
				 clientCouncillorReserve + "\nNobilityTrack: " + clientNobilityTrack
				 +"\nNextKingRewardTile: "+nextKingRewardTile+"\nColourBonuses: "+colourBonuses;
	}
	
	

	
}
