package client.view.notifies;

import java.util.Scanner;

public class ClientMessageNotify implements ClientViewNotify{

	private String message;

	public ClientMessageNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(message);
	}

}
