package modelDTO;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import client.view.notifies.ClientViewNotify;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.playerDTO.ClientPlayerDTO;
import observerPattern.Observable;
import server.model.Game;
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
