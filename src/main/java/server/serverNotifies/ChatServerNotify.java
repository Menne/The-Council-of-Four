package server.serverNotifies;

import java.util.List;

import clientUpdates.ChatMessageUpdate;
import clientUpdates.ClientUpdate;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view a chat message from another player
 * @author cg31
 *
 */
public class ChatServerNotify implements ServerViewNotify {

	private final String message;
	private List<Player> interestedPlayers;

	/**
	 * Constructor of ChatNotify
	 * @param message is the chat message which has to be sent to the client
	 * @param interestedPlayers are the players to whom send the notify
	 */
	public ChatServerNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientUpdate toClientNotify() {
		return new ChatMessageUpdate(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
