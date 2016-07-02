package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ChatMessageDTONotify;
import client.modelDTO.clientNotifies.ClientNotify;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view a chat message from another player
 * @author cg31
 *
 */
public class ChatNotify implements ViewNotify {

	private final String message;
	private List<Player> interestedPlayers;

	/**
	 * Constructor of ChatNotify
	 * @param message is the chat message which has to be sent to the client
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public ChatNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new ChatMessageDTONotify(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
