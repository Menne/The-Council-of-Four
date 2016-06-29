package client.view.notifies;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.view.ClientView;

public class PlayerAcceptedNotify implements ClientViewNotify {

	private final String message;
	private final boolean firstPlayer;
	
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
