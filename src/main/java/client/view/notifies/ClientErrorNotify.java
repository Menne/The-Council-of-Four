package client.view.notifies;

import client.view.ClientView;


public class ClientErrorNotify implements ClientViewNotify {

	private String message;

	public ClientErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayError(this.message);
	}

}
