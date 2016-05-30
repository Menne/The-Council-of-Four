package modelDTO;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import client.view.notifies.ClientViewNotify;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.parser.Parser;
import modelDTO.playerDTO.ClientPlayerDTO;
import observerPattern.Observable;
import server.model.Game;
import server.model.actions.Action;
import modelDTO.gameTableDTO.GameTableDTO;

public class GameDTO extends Observable<ClientViewNotify> implements ModelDTO<Game> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250245390720798602L;
	private GameTableDTO clientGameTable;
	private ClientPlayerDTO clientPlayer;
	private ArrayList<ActionDTO> availableActions;
	private MarketDTO market;
	private Parser parser;
	
	public GameDTO() {
		this.clientGameTable=new GameTableDTO();
		this.clientPlayer=new ClientPlayerDTO();
		this.availableActions=new ArrayList<>();
		this.market=new MarketDTO();
		this.parser=new Parser(this);
	}

	@Override
	public void map(Game realObject) {
			
		if (realObject.getCurrentPlayer()!=null){
			ClientPlayerDTO playerDTO=new ClientPlayerDTO();
			playerDTO.map(realObject.getCurrentPlayer());
			this.clientPlayer=playerDTO;
		}
		
		for (Action action : realObject.getState().getAcceptableActions(realObject))
			this.availableActions.add(action.map());
		
		this.market.map(realObject.getMarket());
	}
	
	
	public GameTableDTO getClientGameTable() {
		return clientGameTable;
	}

	public void setClientGameTable(GameTableDTO clientGameTable) {
		this.clientGameTable = clientGameTable;
	}
	
	public ClientPlayerDTO getClientPlayer() {
		return clientPlayer;
	}

	public void setClientPlayer(ClientPlayerDTO clientPlayer) {
		this.clientPlayer=clientPlayer;
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

	public Parser getParser() {
		return this.parser;
	}

	
}
