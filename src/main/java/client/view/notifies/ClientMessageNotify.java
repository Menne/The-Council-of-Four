package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic for notifying the ClientView a message from the system
 * @author cg31
 *
 */
public class ClientMessageNotify implements ClientViewNotify{

	private String message;

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
