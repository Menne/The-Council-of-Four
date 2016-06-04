package client.view.notifies;

import java.util.Scanner;


public class ClientErrorNotify implements ClientViewNotify {

	private String message;

	public ClientErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(message);
	}

}
