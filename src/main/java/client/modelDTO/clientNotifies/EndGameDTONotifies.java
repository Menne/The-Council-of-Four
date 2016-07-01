package client.modelDTO.clientNotifies;

import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.view.notifies.ClientGameOverNotify;

/**
 * This class represents the notification that the game has just finished
 * @author cg31
 *
 */
public class EndGameDTONotifies implements ClientNotify {

	private static final long serialVersionUID = 3067799541565058885L;
	private final List<GenericPlayerDTO> finalRankingTable;
	
	/**
	 * Constructor of EndGameDTONotifies
	 * @param players is the final status of the player in the game
	 */
	public EndGameDTONotifies(List<GenericPlayerDTO> players) {
		this.finalRankingTable=players;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientGameOverNotify(finalRankingTable));
	}

}
