package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ChatMessageDTONotify;
import client.modelDTO.clientNotifies.ClientNotify;
import server.model.player.Player;

public class ChatNotify implements ViewNotify {

	private final String message;
	private List<Player> interestedPlayers;

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
