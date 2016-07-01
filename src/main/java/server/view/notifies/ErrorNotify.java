package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.ErrorDTONotify;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view an error message from the system
 * @author cg31
 *
 */
public class ErrorNotify implements ViewNotify {

	private String message;
	private List<Player> interestedPlayers;

	/**
	 * Constructor of ErrorNotify
	 * @param message is the error message which has to be sent to the client
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public ErrorNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new ErrorDTONotify(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
