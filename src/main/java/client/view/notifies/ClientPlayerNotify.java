package client.view.notifies;



import client.view.CLI;
import modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerNotify implements ClientViewNotify {

	private ClientPlayerDTO clientPlayerUpdated;

	public ClientPlayerNotify(ClientPlayerDTO clientPlayerDTO) {
		this.clientPlayerUpdated=clientPlayerDTO;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(clientPlayerUpdated);

	}

}
