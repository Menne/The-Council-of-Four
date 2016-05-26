package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import players.Player;

public interface ViewNotify {
	
	public ClientNotify toClientNotify();
	
	public List<Player> sendTo();

}
