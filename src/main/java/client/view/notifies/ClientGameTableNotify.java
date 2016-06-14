package client.view.notifies;

import client.view.ClientView;
import modelDTO.gameTableDTO.GameTableDTO;

public class ClientGameTableNotify implements ClientViewNotify {
	
	private GameTableDTO gameTableUpdated;
	
	public ClientGameTableNotify(GameTableDTO gameTableDTO) {
		this.gameTableUpdated=gameTableDTO;
	}
	
	@Override
	public void updateView(ClientView view) {
		view.displayGameTable(this.gameTableUpdated);
	}

}
