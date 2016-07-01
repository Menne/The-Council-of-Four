package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientGameOverNotify;

/**
 * This class represents the notification that the game has just finished
 * @author cg31
 *
 */
public class EndGameDTONotifies implements ClientNotify {

	private static final long serialVersionUID = 3067799541565058885L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientGameOverNotify());
	}

}
