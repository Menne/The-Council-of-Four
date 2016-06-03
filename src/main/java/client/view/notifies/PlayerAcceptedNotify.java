package client.view.notifies;

import java.util.Scanner;

public class PlayerAcceptedNotify implements ClientViewNotify {

	private String message;
	
	public PlayerAcceptedNotify(String message) {
		this.message=message;
	}
	
	@Override
	public void stamp(Scanner scanner) {
		System.out.println(message);

	}

}
