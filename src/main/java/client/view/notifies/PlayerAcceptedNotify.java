package client.view.notifies;

import client.view.socket.CLIsocket;

public class PlayerAcceptedNotify implements ClientViewNotify {

	private String message;
	
	public PlayerAcceptedNotify(String message) {
		this.message=message;
	}
	
	@Override
	public void stamp(CLIsocket view) {
		System.out.println(message);

	}

}
