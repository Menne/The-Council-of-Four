package client.view.notifies;



import client.view.socket.CLIsocket;
import modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerNotify implements ClientViewNotify {

	private ClientPlayerDTO clientPlayerUpdated;

	public ClientPlayerNotify(ClientPlayerDTO clientPlayerDTO) {
		this.clientPlayerUpdated=clientPlayerDTO;
	}

	@Override
	public void stamp(CLIsocket view) {
		System.out.println(clientPlayerUpdated);

	}

}
