package client.view.notifies;

import client.view.ClientView;

public class ClientChatMessageNotify implements ClientViewNotify {

	private final String message;

	public ClientChatMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayChatMessage(this.message);

	}

}
