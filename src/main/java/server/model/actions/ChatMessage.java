package server.model.actions;

import java.util.ArrayList;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.ChatServerNotify;

/**
 * This class modelizes the action of sending a chat message to everybody
 * @author cg31
 *
 */
public class ChatMessage implements Action {

	private final String message;

	public ChatMessage(String message) {
		this.message=message;
	}

	/**
	 * Sends the chat message to all the clients
	 */
	@Override
	public boolean executeAction(Game game) {
		game.notifyObserver(new ChatServerNotify(this.message, new ArrayList<Player>(game.getAllPlayers())));
		return true;
	}

	@Override
	public ActionDTO map() {
		throw new IllegalStateException("ChatMessage action doesn't require mapping");
	}

	@Override
	public void updateClients(Game game) {
		return;
	}

}
