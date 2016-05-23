package client.ModelDTO;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.UndirectedGraph;

import model.gameTable.CardColour;

public class GameDTO {
	
	private UndirectedGraph<CityDTO, org.jgrapht.graph.DefaultEdge> clientMap;
	private List<RegionDTO> clientRegions;
	private CardColour[] clientKingBalcony;
	private List<CardColour> clientCouncillorReserve;
//	private List<Set<String>> clientNobilityTrack;
	private List<PlayerDTO> clientPlayers;
	private PlayerDTO currentPlayer;
	
	public GameDTO(){
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CardColour[4];
		this.clientCouncillorReserve=new ArrayList<CardColour>();
//		this.clientNobilityTrack=new ArrayList<Set<String>>();
		this.clientPlayers=new ArrayList<PlayerDTO>();
	}

	public UndirectedGraph<CityDTO, org.jgrapht.graph.DefaultEdge> getClientMap() {
		return clientMap;
	}

	public void setClientMap(UndirectedGraph<CityDTO, org.jgrapht.graph.DefaultEdge> clientMap) {
		this.clientMap = clientMap;
	}

	public List<RegionDTO> getClientRegions() {
		return clientRegions;
	}

	public void setClientRegions(List<RegionDTO> clientRegions) {
		this.clientRegions = clientRegions;
	}

	public CardColour[] getClientKingBalcony() {
		return clientKingBalcony;
	}

	public void setClientKingBalcony(CardColour[] clientKingBalcony) {
		this.clientKingBalcony = clientKingBalcony;
	}

	public List<CardColour> getClientCouncillorReserve() {
		return clientCouncillorReserve;
	}

	public void setClientCouncillorReserve(List<CardColour> clientCouncillorReserve) {
		this.clientCouncillorReserve = clientCouncillorReserve;
	}

	public List<PlayerDTO> getClientPlayers() {
		return clientPlayers;
	}

	public void setClientPlayers(List<PlayerDTO> clientPlayers) {
		this.clientPlayers = clientPlayers;
	}

	public PlayerDTO getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(PlayerDTO currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
}
