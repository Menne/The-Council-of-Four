package client.view.notifies;

import java.util.Scanner;

import client.view.ClientView;

public class ClientMessageNotify implements ClientViewNotify{

	private String message;

	public ClientMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(message);
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
	}

}
