package client.clientNotifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView a message from the system
 * @author cg31
 *
 */
public class MessageClientNotify implements ClientViewNotify{

	private final String message;

	/**
	 * Constructor of ClientMessageNotify
	 * @param message is the message sent from the server
	 */
	public MessageClientNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
	}

}
