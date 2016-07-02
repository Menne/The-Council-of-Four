package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic to send an error message to the client view
 * @author cg31
 *
 */
public class ClientErrorNotify implements ClientViewNotify {

	private final String message;

	/**
	 * Constructor of ClientErrorNotify
	 * @param message is the error message
	 */
	public ClientErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayError(this.message);
	}

}
