package client.clientView.notifies;

import client.clientView.CLI;

public class ActionNotify implements ClientViewNotify {

	private String message;

	public ActionNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(message);
	}

}
