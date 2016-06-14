package client.view.notifies;

import java.util.Scanner;

import client.view.ClientView;

public class PlayerAcceptedNotify implements ClientViewNotify {

	private String message;
	
	public PlayerAcceptedNotify(String message) {
		this.message=message;
	}
	
	@Override
	public void stamp(Scanner scanner) {
		System.out.println(message);

	}

	@Override
	public void updateView(ClientView view) {
		// TODO Auto-generated method stub
		
	}

}
