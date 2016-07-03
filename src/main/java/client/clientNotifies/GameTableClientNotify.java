package client.clientNotifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView that the game table is changed and needs to be updated
 * @author cg31
 *
 */
public class GameTableClientNotify implements ClientViewNotify {

	private final boolean startGame;
	
	/**
	 * Constructor of ClientGameTableNotify 
	 * @param startGame is a flag that indicates if the game has just started or not
	 */
	public GameTableClientNotify(boolean startGame) {
		this.startGame=startGame;
	}
	
	@Override
	public void updateView(ClientView view) {
		if(startGame)
			view.startGame();
		view.displayGameTable();
	}

}
