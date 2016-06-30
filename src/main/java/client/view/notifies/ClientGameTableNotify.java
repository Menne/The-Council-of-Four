package client.view.notifies;

import client.modelDTO.gameTableDTO.GameTableDTO;
import client.view.ClientView;

/**
 * This class contains the logic for notifying the ClientView that the game table is changed and needs to be updated
 * @author cg31
 *
 */
public class ClientGameTableNotify implements ClientViewNotify {
	
	private final GameTableDTO gameTableUpdated;
	private final boolean startGame;
	
	/**
	 * Constructor of ClientGameTableNotify 
	 * @param gameTableDTO is the updated game table
	 * @param startGame is a flag that indicates if the game has just started or not
	 */
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
