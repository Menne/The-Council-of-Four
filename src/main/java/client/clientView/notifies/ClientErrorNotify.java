package client.clientView.notifies;

import client.clientView.CLI;

public class ClientErrorNotify implements ClientViewNotify {

	private String message;

	public ClientErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(message);
	}

}
