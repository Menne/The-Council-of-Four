package client.view.notifies;

import client.view.socket.CLIsocket;
import modelDTO.gameTableDTO.GameTableDTO;

public class ClientGameTableNotify implements ClientViewNotify {
	
	private GameTableDTO gameTableUpdated;
	
	public ClientGameTableNotify(GameTableDTO gameTableDTO) {
		this.gameTableUpdated=gameTableDTO;
	}
	
	@Override
	public void stamp(CLIsocket view) {
		System.out.println(this.gameTableUpdated.toString());
	}

}
