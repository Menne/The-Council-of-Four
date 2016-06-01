package client.view.notifies;

import client.view.socket.CLI;

public class ClientMessageNotify implements ClientViewNotify{

	private String message;

	public ClientMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(message);
	}

}
