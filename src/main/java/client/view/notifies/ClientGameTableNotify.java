package client.view.notifies;

import client.view.CLI;
import modelDTO.gameTableDTO.GameTableDTO;

public class ClientGameTableNotify implements ClientViewNotify {
	
	private GameTableDTO gameTableUpdated;
	
	public ClientGameTableNotify(GameTableDTO gameTableDTODTO) {
		this.gameTableUpdated=gameTableDTODTO;
	}
	
	@Override
	public void stamp(CLI view) {
		System.out.println(this.gameTableUpdated);
	}

}
