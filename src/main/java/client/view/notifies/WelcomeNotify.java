package client.view.notifies;

import java.io.IOException;

import client.view.socket.CLIsocket;
import modelDTO.actionsDTO.AddPlayerDTO;

public class WelcomeNotify implements ClientViewNotify {

	@Override
	public void stamp(CLIsocket clientView) {
		AddPlayerDTO actionDTO=new AddPlayerDTO();
		System.out.println("Welcome to a new game of CoF! Please, tell me your name:");
		String input=clientView.getScanner().nextLine();
		actionDTO.setPlayerName(input);
		try {
			
			clientView.getSocketOut().writeObject(actionDTO);
			clientView.getSocketOut().flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
