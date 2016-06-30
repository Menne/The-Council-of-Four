package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic for sending an error to the client view
 * @author cg31
 *
 */
public class ClientErrorNotify implements ClientViewNotify {

	private String message;

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
