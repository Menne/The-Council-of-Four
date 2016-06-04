package client.view.notifies;

import java.util.Scanner;

import modelDTO.gameTableDTO.GameTableDTO;

public class ClientGameTableNotify implements ClientViewNotify {
	
	private GameTableDTO gameTableUpdated;
	
	public ClientGameTableNotify(GameTableDTO gameTableDTO) {
		this.gameTableUpdated=gameTableDTO;
	}
	
	@Override
	public void stamp(Scanner scanner) {
		System.out.println(this.gameTableUpdated.toString());
	}

}
