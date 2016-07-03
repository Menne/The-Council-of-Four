package server.serverNotifies;

import java.util.List;

import clientUpdates.ClientUpdate;
import clientUpdates.MessageUpdate;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view a message from the system
 * @author cg31
 *
 */
public class MessageServerNotify implements ServerViewNotify {

	private String message;
	private List<Player> interestedPlayers;

	/**
	 * Constructor of MessageNotify
	 * @param message is the message which has to be sent to the client
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public MessageServerNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientUpdate toClientNotify() {
		return new MessageUpdate(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
