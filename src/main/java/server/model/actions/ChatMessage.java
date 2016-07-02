package server.model.actions;

import java.util.ArrayList;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.player.Player;
import server.view.notifies.ChatNotify;

public class ChatMessage implements Action {

	private final String message;

	public ChatMessage(String message) {
		this.message=message;
	}

	@Override
	public boolean executeAction(Game game) {
		game.notifyObserver(new ChatNotify(this.message, new ArrayList<Player>(game.getPlayers())));
		return true;
	}

	@Override
	public ActionDTO map() {
		throw new IllegalStateException("ChatMessage action doesn't require mapping");
	}

	@Override
	public void notifyPlayers(Game game) {
		return;
	}

}
