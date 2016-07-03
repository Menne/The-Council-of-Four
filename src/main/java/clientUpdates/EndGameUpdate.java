package clientUpdates;

import java.util.List;

import client.clientNotifies.GameOverClientNotify;
import client.modelDTO.GameDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;

/**
 * This class represents the notification that the game has just finished
 * @author cg31
 *
 */
public class EndGameUpdate implements ClientUpdate {

	private static final long serialVersionUID = 3067799541565058885L;
	private final List<GenericPlayerDTO> finalRankingTableDTO;

	public EndGameUpdate(List<GenericPlayerDTO> finalRankingTableDTO) {
		this.finalRankingTableDTO=finalRankingTableDTO;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new GameOverClientNotify(this.finalRankingTableDTO));
	}

}
