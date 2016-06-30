package client.view.notifies;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.view.ClientView;

/**
 * This class modelizes a notify which is sent after a client puts itself waiting for a new game.
 * It contains the logic to notify the ClientView that the player has registered successfully
 * @author cg31
 *
 */
public class PlayerAcceptedNotify implements ClientViewNotify {

	private final String message;
	private final boolean firstPlayer;
	
	/**
	 * Constructor of PlayerAcceptedNotify
	 * @param message is a welcome message
	 * @param isFirstPlayer is a flag that indicates if the client registered is the first player
	 */
	public PlayerAcceptedNotify(String message, boolean isFirstPlayer) {
		this.message=message;
		this.firstPlayer=isFirstPlayer;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
		if(firstPlayer)
			try {
				view.askForMap();
			} catch (RemoteException e) {
				Logger logger=Logger.getAnonymousLogger();
				logger.log(Level.SEVERE, "Failed to send action with RMI", e);
			}
	}

}
