package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.MessageDTONotify;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view a message from the system
 * @author cg31
 *
 */
public class MessageNotify implements ViewNotify {

	private String message;
	private List<Player> interestedPlayers;

	/**
	 * Constructor of MessageNotify
	 * @param message is the message which has to be sent to the client
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public MessageNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new MessageDTONotify(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
