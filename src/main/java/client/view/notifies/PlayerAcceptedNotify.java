package client.view.notifies;

import client.view.socket.CLI;

public class PlayerAcceptedNotify implements ClientViewNotify {

	private String message;
	
	public PlayerAcceptedNotify(String message) {
		this.message=message;
	}
	
	@Override
	public void stamp(CLI view) {
		System.out.println(message);

	}

}
