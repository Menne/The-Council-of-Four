package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic for sending a chat message to the ClientView
 * @author cg31
 *
 */
public class ClientChatMessageNotify implements ClientViewNotify {

	private final String message;

	/**
	 * Constructor of ClientChatMessageNotify
	 * @param message is the message sent from another player
	 */
	public ClientChatMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayChatMessage(this.message);
	}

}
