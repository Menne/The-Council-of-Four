package client.view.notifies;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.view.ClientView;

/**
 * This class provides the logic to notify the server view which actions a player can do in the current game state
 * @author cg31
 *
 */
public class ClientAvailableActionsNotify implements ClientViewNotify {

	private final List<ActionDTO> availableActions;
	private final String message;

	/**
	 * Constructor of ClientAvailableActionsNotify
	 * @param availableActions are the available actions in the current game state
	 * @param message is a notification the system offers to the player to explain in which phase of the game he is
	 */
	public ClientAvailableActionsNotify(List<ActionDTO> availableActions, String message) {
		this.availableActions=availableActions;
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
		view.displayAvailableActions();
		
		if(!availableActions.isEmpty()){
			view.getConnection().setTimerTask(new TimerTask() {
				
				@Override
				public void run() {
					try {
						view.getConnection().sendAction(new QuitDTO());
					} catch (RemoteException e) {
						Logger logger=Logger.getAnonymousLogger();
						logger.log(Level.SEVERE, "Failed to send action with RMI", e);
					}			
				}
			});
			view.getConnection().getTimer().schedule(view.getConnection().getTimerTask(), 300*1000L);
		}
		
	}

}
