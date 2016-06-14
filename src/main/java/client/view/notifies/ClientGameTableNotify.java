package client.view.notifies;

import client.modelDTO.gameTableDTO.GameTableDTO;
import client.view.ClientView;

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
