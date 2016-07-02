package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView that the game is over
 * @author cg31
 *
 */
public class ClientGameOverNotify implements ClientViewNotify {
	
	@Override
	public void updateView(ClientView view) {
		view.displayFinalRanking();
	}

}
