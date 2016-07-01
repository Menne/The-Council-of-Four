package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import server.model.player.Player;

/**
 * This class modelizes a generic notify from server model to server view
 * @author cg31
 *
 */
public interface ViewNotify {
	
	/**
	 * This method purpose is to pack all the changes in the server game in an object DTO and send 
	 * it to the client
	 * @return an object with all the changes in the server game
	 */
	public ClientNotify toClientNotify();
	
	/**
	 * This methods calculates all the players to whom send the changes of the game or the message
	 * @return the list of players to whom send the changes of the game
	 */
	public List<Player> sendTo();

}
