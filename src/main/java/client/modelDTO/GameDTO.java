package client.modelDTO;

import java.util.ArrayList;
import java.util.List;

import client.clientNotifies.ClientViewNotify;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import observerPattern.Observable;

/**
 * This class provides all the informations about the game, but without logic
 * @author cg31
 *
 */
public class GameDTO extends Observable<ClientViewNotify> implements ModelDTO {
	
	private static final long serialVersionUID = 8250245390720798602L;
	private GameTableDTO clientGameTable;
	private ClientPlayerDTO clientPlayer;
	private ArrayList<ActionDTO> availableActions;
	private MarketDTO market;
	
	/**
	 * Constructor of GameDTO
	 */
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
