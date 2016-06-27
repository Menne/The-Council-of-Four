package client.view.notifies;

import client.modelDTO.gameTableDTO.GameTableDTO;
import client.view.ClientView;

public class ClientGameTableNotify implements ClientViewNotify {
	
	private final GameTableDTO gameTableUpdated;
	private final boolean startGame;
	
	public ClientGameTableNotify(GameTableDTO gameTableDTO, boolean startGame) {
		this.gameTableUpdated=gameTableDTO;
		this.startGame=startGame;
	}
	
	@Override
	public void updateView(ClientView view) {
		if(startGame)
			view.startGame(gameTableUpdated);
		view.displayGameTable(this.gameTableUpdated);
	}

}
