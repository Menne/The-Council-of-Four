package client.view.notifies;



import java.util.Scanner;

import client.view.ClientView;
import modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerNotify implements ClientViewNotify {

	private ClientPlayerDTO clientPlayerUpdated;

	public ClientPlayerNotify(ClientPlayerDTO clientPlayerDTO) {
		this.clientPlayerUpdated=clientPlayerDTO;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(clientPlayerUpdated);

	}

	@Override
	public void updateView(ClientView view) {
		// TODO Auto-generated method stub
		
	}

}
