package client.view.notifies;

import client.view.CLI;

public class PlayerUpdatedNotify implements ClientViewNotify {

	private String message;
	
	public PlayerUpdatedNotify(String message) {
		this.message=message;
	}
	
	@Override
	public void stamp(CLI view) {
		System.out.println(message);

	}

}
