package modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import modelDTO.ModelDTO;
import client.view.notifies.ClientViewNotify;
import modelDTO.gameTableDTO.RegionDTO;
import observerPattern.Observable;
import players.Player;
import server.model.Game;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.RegionBoard;
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
	private ArrayList<GenericPlayerDTO> Players;
	private String currentPlayer;
	
	public GameTableDTO() {
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CardColourDTO[4];
		this.clientCouncillorReserve=new ArrayList<CardColourDTO>();
		this.clientNobilityTrack=new ArrayList<Set<Bonus>>();
		this.Players=new ArrayList<GenericPlayerDTO>();
	}

	@Override
	public void map(Game realObject) {
		
		for(RegionBoard region : realObject.getGameTable().getRegionBoards()){
			RegionDTO regionDTO=new RegionDTO();
			regionDTO.map(region);
			this.clientRegions.add(regionDTO);
		}
		
		for(int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++){
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(realObject.getGameTable().getCouncilOfKing().getCouncillors()[i].getColour());
			this.clientKingBalcony[i]=cardColourDTO;
		}
		
		for(Councillor councillor : realObject.getGameTable().getCouncilReserve().getCouncillors()){
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(councillor.getColour());
			this.clientCouncillorReserve.add(cardColourDTO);
		}
		
		for(Player player : realObject.getPlayers()) {
			GenericPlayerDTO playerDTO=new GenericPlayerDTO();
			playerDTO.map(player);
			this.Players.add(playerDTO);
		}
		
		this.clientNobilityTrack=(ArrayList<Set<Bonus>>) realObject.getGameTable().getNobilityTrack().getTrack();
	
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
		return Players;
	}

	public void setClientPlayers(List<GenericPlayerDTO> clientPlayers) {
		this.Players = (ArrayList<GenericPlayerDTO>) clientPlayers;
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	
	@Override
	public String toString() {
		return "\nUpdated game table:\nPlayers:\n" + Players + "\nNow is plaiyng:\t" + currentPlayer + "\n" +
				 clientRegions + " \nKing's balcony:" + Arrays.toString(clientKingBalcony) + "\nCouncillors riserve"+
				 clientCouncillorReserve + "\n" + clientNobilityTrack;
	}
	
	

	
}
