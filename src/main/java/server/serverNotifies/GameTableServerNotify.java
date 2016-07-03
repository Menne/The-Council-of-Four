package server.serverNotifies;

import java.util.List;

import clientUpdates.ClientUpdate;
import clientUpdates.GameTableUpdate;
import server.model.Game;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view that the game table status is changed
 * @author cg31
 *
 */
public class GameTableServerNotify implements ServerViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	private final boolean startGame;
	
	/**
	 * Constructor of GameTableNotify
	 * @param game is the updated game status
	 * @param interestedPlayers are the players to whom send the notify
	 * @param startGame is a flag that indicates the game has just started
	 */
	public GameTableServerNotify(Game game, List<Player> interestedPlayers, boolean startGame) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
		this.startGame=startGame;
	}
	
	@Override
	public ClientUpdate toClientNotify() {
		return new GameTableUpdate(this.game.getGameMapper().gameTableMap(this.game),startGame);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}
	
	public Player getCurrentPlayer(){
		return game.getCurrentPlayer();
	}

}
