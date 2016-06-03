package client.view.notifies;

import client.view.socket.CLIsocket;

public class ClientMessageNotify implements ClientViewNotify{

	private String message;

	public ClientMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLIsocket view) {
		System.out.println(message);
	}

}
