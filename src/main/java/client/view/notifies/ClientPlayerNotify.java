package client.view.notifies;

/**
 * This class contains the logic for notifying the ClientView that the player status has changed and needs to be updated
 * @author cg31
 *
 */
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.ClientView;

public class ClientPlayerNotify implements ClientViewNotify {

	private ClientPlayerDTO clientPlayerUpdated;

	/**
	 * Constructor of ClientPlayerNotify
	 * @param clientPlayerDTO is the client player updated
	 */
	public ClientPlayerNotify(ClientPlayerDTO clientPlayerDTO) {
		this.clientPlayerUpdated=clientPlayerDTO;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayPlayer(this.clientPlayerUpdated);
	}

}
