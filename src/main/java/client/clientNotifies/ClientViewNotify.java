package client.clientNotifies;

import client.view.ClientView;

/**
 * This class modelizes a generic notify sent from the ClientGame to the ClientView in order to notify 
 * changes of the game status or messages from system or other players
 * @author cg31
 *
 */
@FunctionalInterface
public interface ClientViewNotify {
	
	/**
	 * Updates the ClientView invoking the appropriate method according to the incoming notify
	 * @param view
	 */
	public void updateView(ClientView view);

}
