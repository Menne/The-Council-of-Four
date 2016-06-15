package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import server.model.player.Player;

public interface ViewNotify {
	
	public ClientNotify toClientNotify();
	
	public List<Player> sendTo();

}
