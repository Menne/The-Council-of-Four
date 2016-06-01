package client.view.notifies;

import client.view.socket.CLI;
import modelDTO.actionsDTO.AddPlayerDTO;

public class WelcomeNotify implements ClientViewNotify {

	@Override
	public void stamp(CLI clientView) {
		AddPlayerDTO actionDTO=new AddPlayerDTO();
		System.out.println("Welcome to a new game of CoF! Please, tell me your name:");
		String input=clientView.getScanner().nextLine();
		actionDTO.setPlayerName(input);
		clientView.notifyObserver(actionDTO);
	}

}
