package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView a message from the system
 * @author cg31
 *
 */
public class ClientMessageNotify implements ClientViewNotify{

	private final String message;

	/**
	 * Constructor of ClientMessageNotify
	 * @param message is the message sent from the server
	 */
	public ClientMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
	}

}
