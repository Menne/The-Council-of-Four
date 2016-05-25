package client.clientView.notifies;

import client.actionDTO.AddPlayerDTO;
import client.clientView.CLI;

public class WelcomeNotify implements ClientViewNotify {

	@Override
	public void stamp(CLI clientView) {
		AddPlayerDTO addPlayerDTO=new AddPlayerDTO();
		System.out.println("Welcome to a new game of CoF! Please, tell me your name:");
		String input=clientView.getScanner().nextLine();
		addPlayerDTO.setPlayerName(input);
		clientView.notifyObserver(addPlayerDTO);
	}

}
