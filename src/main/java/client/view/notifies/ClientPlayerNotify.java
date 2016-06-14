package client.view.notifies;



import client.view.ClientView;
import modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerNotify implements ClientViewNotify {

	private ClientPlayerDTO clientPlayerUpdated;

	public ClientPlayerNotify(ClientPlayerDTO clientPlayerDTO) {
		this.clientPlayerUpdated=clientPlayerDTO;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayPlayer(this.clientPlayerUpdated);
	}

}
