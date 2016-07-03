package client.controller;

import client.modelDTO.GameDTO;
import clientUpdates.ClientUpdate;
import observerPattern.Observer;

/**
 * This is the controller of the MVC pattern of the client
 * @author cg31
 *
 */
public class ClientController implements Observer<ClientUpdate> {

	private GameDTO clientGame;
	
	/**
	 * Constructor of ClientController
	 * @param clientGame is the client game
	 */
	public ClientController(GameDTO clientGame) {
		this.clientGame=clientGame;
	}
	
	/**
	 * Updates the client view that the status of the game has changed
	 */
	@Override
	public void update(ClientUpdate notify) {
		notify.updateModel(clientGame);	
	}

}
