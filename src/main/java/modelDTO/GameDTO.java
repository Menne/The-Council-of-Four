package modelDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import modelDTO.actionsDTO.ActionDTO;
import client.view.notifies.ClientViewNotify;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.parser.Parser;
import modelDTO.playerDTO.PlayerDTO;
import observerPattern.Observable;
import players.Player;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.RegionBoard;
import modelDTO.gameTableDTO.CardColourDTO;

public class GameDTO extends Observable<ClientViewNotify> implements ModelDTO<Game> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250245390720798602L;
	private ArrayList<RegionDTO> clientRegions;
	private CardColourDTO[] clientKingBalcony;
	private ArrayList<CardColourDTO> clientCouncillorReserve;
	private ArrayList<Set<Bonus>> clientNobilityTrack;
	private ArrayList<PlayerDTO> clientPlayers;
	private PlayerDTO currentPlayer;
	private ArrayList<ActionDTO> availableActions;
	private MarketDTO market;
	private Parser parser;
	
	public GameDTO(){
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CardColourDTO[4];
		this.clientCouncillorReserve=new ArrayList<CardColourDTO>();
		this.clientNobilityTrack=new ArrayList<Set<Bonus>>();
		this.clientPlayers=new ArrayList<PlayerDTO>();
		this.availableActions=new ArrayList<ActionDTO>();
		this.market=new MarketDTO();
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
		
		this.clientNobilityTrack=(ArrayList<Set<Bonus>>) realObject.getGameTable().getNobilityTrack().getTrack();
			
		if(realObject.getCurrentPlayer()!=null){
			PlayerDTO playerDTO=new PlayerDTO();
			playerDTO.map(realObject.getCurrentPlayer());
			this.currentPlayer=playerDTO;
		}
		
		for(Action action : realObject.getState().getAcceptableActions(realObject))
			this.availableActions.add(action.map());
		
		this.market.map(realObject.getMarket());
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

	public List<PlayerDTO> getClientPlayers() {
		return clientPlayers;
	}

	public void setClientPlayers(List<PlayerDTO> clientPlayers) {
		this.clientPlayers = (ArrayList<PlayerDTO>) clientPlayers;
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
		this.availableActions = (ArrayList<ActionDTO>) availableActions;
	}
	

	public MarketDTO getMarket() {
		return market;
	}

	public void setMarket(MarketDTO market) {
		this.market = market;
	}

	
	@Override
	public String toString() {
		return "\nGameDTO\n Players:\n" + clientPlayers + "\n\nCurrentPlayer:\n" + currentPlayer + "\n" +
				 clientRegions + " \n "+ Arrays.toString(clientKingBalcony) +"\n"+
				 clientCouncillorReserve + "\n" + clientNobilityTrack ;
	}

	
	public Parser getParser() {
		return this.parser;
	}
	
}
