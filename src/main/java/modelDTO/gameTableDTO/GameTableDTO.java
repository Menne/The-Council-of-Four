package modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import modelDTO.ModelDTO;
import client.view.notifies.ClientViewNotify;
import modelDTO.gameTableDTO.RegionDTO;
import observerPattern.Observable;
import server.model.Game;
import server.model.bonus.Bonus;
import modelDTO.gameTableDTO.CardColourDTO;

public class GameTableDTO extends Observable<ClientViewNotify> implements ModelDTO<Game> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250245390720798602L;
	private ArrayList<RegionDTO> clientRegions;
	private CardColourDTO[] clientKingBalcony;
	private ArrayList<CardColourDTO> clientCouncillorReserve;
	private ArrayList<Set<Bonus>> clientNobilityTrack;
	private ArrayList<GenericPlayerDTO> players;
	private String currentPlayer;
	private String king;
	
	public GameTableDTO() {
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CardColourDTO[4];
		this.clientCouncillorReserve=new ArrayList<CardColourDTO>();
		this.clientNobilityTrack=new ArrayList<Set<Bonus>>();
		this.players=new ArrayList<GenericPlayerDTO>();
	}

	
	public List<RegionDTO> getClientRegions() {
		return clientRegions;
	}

	public void setClientRegions(List<RegionDTO> clientRegions) {
		this.clientRegions = (ArrayList<RegionDTO>) clientRegions;
	}

	public CardColourDTO[] getClientKingBalcony() {
		return clientKingBalcony;
	}

	public void setClientKingBalcony(CardColourDTO[] clientKingBalcony) {
		this.clientKingBalcony = clientKingBalcony;
	}

	public List<CardColourDTO> getClientCouncillorReserve() {
		return clientCouncillorReserve;
	}

	public void setClientCouncillorReserve(List<CardColourDTO> clientCouncillorReserve) {
		this.clientCouncillorReserve = (ArrayList<CardColourDTO>) clientCouncillorReserve;
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

	@Override
	public String toString() {
		return "\nUpdated game table:\nPlayers:\n" + players + "\nNow is plaiyng:\t" + currentPlayer + "\n" +
				 clientRegions + "\nKingCity: "+ king+" \nKing's balcony:" + Arrays.toString(clientKingBalcony) + "\nCouncillors riserve"+
				 clientCouncillorReserve + "\n" + clientNobilityTrack;
	}
	
	

	
}
