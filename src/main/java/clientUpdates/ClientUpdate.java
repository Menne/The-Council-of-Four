package clientUpdates;

import java.io.Serializable;

import client.modelDTO.GameDTO;

/**
 * This class modelizes a generic game update from server to client
 * @author cg31
 *
 */
@FunctionalInterface
public interface ClientUpdate extends Serializable{

	/**
	 * This method first updates the client game, then lets the client game notify the view 
	 * that something in the game status has changed
	 * @param gameDTOtoupdate is the client game that has to be updated
	 */
	public void updateModel(GameDTO gameDTOtoupdate);
}
