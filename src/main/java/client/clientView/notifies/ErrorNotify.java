package client.clientView.notifies;

import client.clientView.CLI;

public class ErrorNotify implements ClientViewNotify {

	private String message;

	public ErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(message);
	}

}
