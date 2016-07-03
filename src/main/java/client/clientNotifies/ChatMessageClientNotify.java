package client.clientNotifies;

import client.view.ClientView;

/**
 * This class contains the logic to send a chat message to the ClientView
 * @author cg31
 *
 */
public class ChatMessageClientNotify implements ClientViewNotify {

	private final String message;

	/**
	 * Constructor of ClientChatMessageNotify
	 * @param message is the message sent from another player
	 */
	public ChatMessageClientNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayChatMessage(this.message);
	}

}
