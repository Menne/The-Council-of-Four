package server.serverNotifies;

import java.util.List;

import clientUpdates.ClientUpdate;
import clientUpdates.PlayerUpdate;
import server.model.Game;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view that the status of a player is changed
 * @author cg31
 *
 */
public class PlayerServerNotify implements ServerViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	private final Player player;
	
	/**
	 * Constructor of PlayerNotify
	 * @param game is the updated game status
	 * @param player is the updated player status
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public PlayerServerNotify(Game game, Player player, List<Player> interestedPlayers) {
		this.game=game;
		this.player=player;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientUpdate toClientNotify() {
		return new PlayerUpdate(this.game.getGameMapper().clientPlayerMap(this.player));
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
