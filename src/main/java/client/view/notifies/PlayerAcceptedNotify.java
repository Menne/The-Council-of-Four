package client.view.notifies;

import client.view.ClientView;

public class PlayerAcceptedNotify implements ClientViewNotify {

	private String message;
	
	public PlayerAcceptedNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
	}

}
