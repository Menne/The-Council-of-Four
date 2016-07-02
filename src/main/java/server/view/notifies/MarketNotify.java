package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.MarketDTONotify;
import server.model.Game;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view that the market status is changed
 * @author cg31
 *
 */
public class MarketNotify implements ViewNotify {
	
	private Game game;
	private List<Player> interestedPlayers;
	private final boolean startMarket;
	private final boolean closeMarket;

	/**
	 * Constructor of MarketNotify
	 * @param game is the updated game status
	 * @param interestedPlayers are the players to whom send the notify
	 * @param startMarket is a flag that indicates the market phase has just started
	 * @param closeMarket is a flag that indicates the market phase has just finished
	 */
	public MarketNotify(Game game, List<Player> interestedPlayers, boolean startMarket, boolean closeMarket) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
		this.startMarket=startMarket;
		this.closeMarket=closeMarket;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new MarketDTONotify(this.game.getGameMapper().marketMap
				(this.game.getMarket()), this.startMarket, this.closeMarket);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
