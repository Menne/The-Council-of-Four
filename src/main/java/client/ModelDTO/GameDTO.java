package client.ModelDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import client.actionDTO.ActionDTO;
import client.clientView.notifies.ClientViewNotify;
import client.parser.Parser;
import observerPattern.Observable;
import players.Player;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.RegionBoard;

public class GameDTO extends Observable<ClientViewNotify> implements ModelDTO<Game> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250245390720798602L;
	private List<RegionDTO> clientRegions;
	private CardColourDTO[] clientKingBalcony;
	private List<CardColourDTO> clientCouncillorReserve;
	private List<Set<Bonus>> clientNobilityTrack;
	private List<PlayerDTO> clientPlayers;
	private PlayerDTO currentPlayer;
	private List<ActionDTO> availableActions;
	private Parser parser;
	
	public GameDTO(){
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CardColourDTO[4];
		this.clientCouncillorReserve=new ArrayList<CardColourDTO>();
		this.clientNobilityTrack=new ArrayList<Set<Bonus>>();
		this.clientPlayers=new ArrayList<PlayerDTO>();
		this.parser=new Parser(this);
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
		
		for(Player player : realObject.getPlayers()){
			PlayerDTO playerDTO=new PlayerDTO();
			playerDTO.map(player);
			this.clientPlayers.add(playerDTO);
		}
		
		this.clientNobilityTrack=realObject.getGameTable().getNobilityTrack().getTrack();
		
		PlayerDTO playerDTO=new PlayerDTO();
		playerDTO.map(realObject.getCurrentPlayer());
		this.currentPlayer=playerDTO;
		
		for(Action action : realObject.getState().getAcceptableActions(realObject))
			availableActions.add(action.map());
	}

	
	public List<RegionDTO> getClientRegions() {
		return clientRegions;
	}

	public void setClientRegions(List<RegionDTO> clientRegions) {
		this.clientRegions = clientRegions;
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
		this.clientCouncillorReserve = clientCouncillorReserve;
	}

	public List<Set<Bonus>> getClientNobilityTrack() {
		return clientNobilityTrack;
	}

	public void setClientNobilityTrack(List<Set<Bonus>> clientNobilityTrack) {
		this.clientNobilityTrack = clientNobilityTrack;
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

	public List<ActionDTO> getAvailableActions() {
		return availableActions;
	}

	public void setAvailableActions(List<ActionDTO> availableActions) {
		this.availableActions = availableActions;
	}

	@Override
	public String toString() {
		return "GameDTO [clientRegions=" + clientRegions + ", clientKingBalcony="
				+ Arrays.toString(clientKingBalcony) + ", clientCouncillorReserve=" + clientCouncillorReserve
				+ ", clientPlayers=" + clientPlayers + ", currentPlayer=" + currentPlayer + ", availableActions="
				+ availableActions + "]";
	}

	
	public Parser getParser() {
		return this.parser;
	}
	
}
