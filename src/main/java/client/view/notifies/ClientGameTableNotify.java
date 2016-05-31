package client.view.notifies;

import client.view.CLI;
import modelDTO.gameTableDTO.GameTableDTO;

public class ClientGameTableNotify implements ClientViewNotify {
	
	private GameTableDTO gameTableUpdated;
	
	public ClientGameTableNotify(GameTableDTO gameTableDTO) {
		this.gameTableUpdated=gameTableDTO;
	}
	
	@Override
	public void stamp(CLI view) {
		System.out.println("Player " + this.gameTableUpdated.getCurrentPlayer() +
				" has done his move: " + this.gameTableUpdated.toString());
	}

}