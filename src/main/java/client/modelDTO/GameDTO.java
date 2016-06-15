package client.modelDTO;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientViewNotify;
import observerPattern.Observable;
import server.model.Game;

public class GameDTO extends Observable<ClientViewNotify> implements ModelDTO<Game> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8250245390720798602L;
	private GameTableDTO clientGameTable;
	private ClientPlayerDTO clientPlayer;
	private ArrayList<ActionDTO> availableActions;
	private MarketDTO market;
	
	public GameDTO() {
		this.clientGameTable=new GameTableDTO();
		this.clientPlayer=new ClientPlayerDTO();
		this.availableActions=new ArrayList<>();
		this.market=new MarketDTO();
	}

	
	public GameTableDTO getClientGameTable() {
		return this.clientGameTable;
	}

	public void setClientGameTable(GameTableDTO clientGameTable) {
		this.clientGameTable=clientGameTable;
	}
	
	public ClientPlayerDTO getClientPlayer() {
		return this.clientPlayer;
	}

	public void setClientPlayer(ClientPlayerDTO clientPlayer) {
		this.clientPlayer=clientPlayer;
	}

	public List<ActionDTO> getAvailableActions() {
		return this.availableActions;
	}

	public void setAvailableActions(List<ActionDTO> availableActions) {
		this.availableActions = (ArrayList<ActionDTO>) availableActions;
	}

	public MarketDTO getMarket() {
		return this.market;
	}

	public void setMarket(MarketDTO market) {
		this.market=market;
	}

	
}
