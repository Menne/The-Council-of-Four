package client.view.notifies;

import client.view.socket.CLIsocket;

public class ActionNotify implements ClientViewNotify {

	private String message;

	public ActionNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLIsocket view) {
		System.out.println(message);
	}

}
