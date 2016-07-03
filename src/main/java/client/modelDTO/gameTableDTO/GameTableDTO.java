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

	/**
	 * @return the region boards of the game table DTO
	 */
	public List<RegionDTO> getClientRegions() {
		return this.clientRegions;
	}

	/**
	 * Sets the regions of the game table DTO
	 * @param clientRegions are the regions DTO
	 */
	public void setClientRegions(List<RegionDTO> clientRegions) {
		this.clientRegions = (ArrayList<RegionDTO>) clientRegions;
	}

	/**
	 * @return the king balcony of the game table DTO
	 */
	public CouncillorDTO[] getClientKingBalcony() {
		return this.clientKingBalcony;
	}

	/**
	 * Sets the king balcony of the game table DTO
	 * @param clientKingBalcony is the king balcony DTO
	 */
	public void setClientKingBalcony(CouncillorDTO[] clientKingBalcony) {
		this.clientKingBalcony = clientKingBalcony;
	}

	/**
	 * @return the councillor reserve of the game table DTO
	 */
	public List<CouncillorDTO> getClientCouncillorReserve() {
		return this.clientCouncillorReserve;
	}

	/**
	 * Sets the councillor reserve of the game table DTO
	 * @param clientCouncillorReserve is the councillors reserve DTO
	 */
	public void setClientCouncillorReserve(List<CouncillorDTO> clientCouncillorReserve) {
		this.clientCouncillorReserve=clientCouncillorReserve;
	}

	/**
	 * @return the bonuses of the nobility track of the game table DTO
	 */
	public List<Set<Bonus>> getClientNobilityTrack() {
		return this.clientNobilityTrack;
	}

	/**
	 * Sets the bonuses of the game table DTO
	 * @param clientNobilityTrack is the nobility track DTO
	 */
	public void setClientNobilityTrack(List<Set<Bonus>> clientNobilityTrack) {
		this.clientNobilityTrack = (ArrayList<Set<Bonus>>) clientNobilityTrack;
	}

	/**
	 * @return the generic players of the game table DTO
	 */
	public List<GenericPlayerDTO> getClientPlayers() {
		return this.players;
	}

	/**
	 * Sets the regions of the game table DTO
	 * @param clientPlayers are the generic players of the game DTO
	 */
	public void setClientPlayers(List<GenericPlayerDTO> clientPlayers) {
		this.players = (ArrayList<GenericPlayerDTO>) clientPlayers;
	}

	/**
	 * @return the current player of the game table DTO
	 */
	public String getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Sets the player of the game table DTO
	 * @param currentPlayer is the client player DTO
	 */
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the king of the game table DTO
	 */
	public String getKing() {
		return this.king;
	}

	/**
	 * Sets the king of the game table DTO
	 * @param king is the king DTO
	 */
	public void setKing(String king) {
		this.king = king;
	}

	/**
	 * @return the next reward tile of the game table DTO
	 */
	public BonusTileDTO getNextKingRewardTile() {
		return this.nextKingRewardTile;
	}

	/**
	 * Sets the next king reward tile of the game table DTO
	 * @param nextKingRewardTile is the next king reward tile
	 */
	public void setNextKingRewardTile(BonusTileDTO nextKingRewardTile) {
		this.nextKingRewardTile = nextKingRewardTile;
	}

	/**
	 * @return the colour bonuses of the game table DTO
	 */
	public Set<BonusTileDTO> getColourBonuses() {
		return this.colourBonuses;
	}

	/**
	 * Sets the colour bonuses of the game table DTO
	 * @param colourBonuses are the colour bonuses
	 */
	public void setColourBonuses(Set<BonusTileDTO> colourBonuses) {
		this.colourBonuses = colourBonuses;
	}
	
	/**
	 * @return the map number of the game table DTO
	 */
	public int getMapNumber() {
		return this.mapNumber;
	}

	/**
	 * Sets the map number of the game table DTO
	 * @param mapNumber is the number of the map of the game DTO
	 */
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
