package server.serverNotifies;

import java.util.List;

import clientUpdates.ClientUpdate;
import clientUpdates.ErrorUpdate;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view an error message from the system
 * @author cg31
 *
 */
public class ErrorServerNotify implements ServerViewNotify {

	private String message;
	private List<Player> interestedPlayers;

	/**
	 * Constructor of ErrorNotify
	 * @param message is the error message which has to be sent to the client
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public ErrorServerNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientUpdate toClientNotify() {
		return new ErrorUpdate(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
