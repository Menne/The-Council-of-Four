package client.clientNotifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView that the player status has changed and needs to be updated
 * @author cg31
 *
 */
public class PlayerClientNotify implements ClientViewNotify {

	@Override
	public void updateView(ClientView view) {
		view.displayPlayer();
	}

}
