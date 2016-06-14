package client.view.notifies;



import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.ClientView;

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
