package client.view.notifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView that the game table is changed and needs to be updated
 * @author cg31
 *
 */
public class ClientGameTableNotify implements ClientViewNotify {

	private final boolean startGame;
	
	/**
	 * Constructor of ClientGameTableNotify 
	 * @param startGame is a flag that indicates if the game has just started or not
	 */
	public ClientGameTableNotify(boolean startGame) {
		this.startGame=startGame;
	}
	
	@Override
	public void updateView(ClientView view) {
		if(startGame)
			view.startGame();
		view.displayGameTable();
	}

}
