package clientUpdates;

import client.clientNotifies.GameOverClientNotify;
import client.modelDTO.GameDTO;

/**
 * This class represents the notification that the game has just finished
 * @author cg31
 *
 */
public class EndGameUpdate implements ClientUpdate {

	private static final long serialVersionUID = 3067799541565058885L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new GameOverClientNotify());
	}

}
