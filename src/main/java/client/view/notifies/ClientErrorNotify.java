package client.view.notifies;

import client.view.socket.CLIsocket;

public class ClientErrorNotify implements ClientViewNotify {

	private String message;

	public ClientErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLIsocket view) {
		System.out.println(message);
	}

}
