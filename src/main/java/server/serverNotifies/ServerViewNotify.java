package server.serverNotifies;

import java.util.List;

import clientUpdates.ClientUpdate;
import server.model.player.Player;

/**
 * This class modelizes a generic notify from server model to server view
 * @author cg31
 *
 */
public interface ServerViewNotify {
	
	/**
	 * This method purpose is to pack all the changes in the server game in an object DTO and send 
	 * it to the client
	 * @return an object with all the changes in the server game
	 */
	public ClientUpdate toClientNotify();
	
	/**
	 * This methods calculates all the players to whom send the changes of the game or the message
	 * @return the list of players to whom send the changes of the game
	 */
	public List<Player> sendTo();

}
