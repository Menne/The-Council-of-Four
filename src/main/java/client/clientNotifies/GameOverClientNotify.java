package client.clientNotifies;

import java.util.List;

import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView that the game is over
 * @author cg31
 *
 */
public class GameOverClientNotify implements ClientViewNotify {
	
	private final List<GenericPlayerDTO> finalRankingTableDTO;

	public GameOverClientNotify(List<GenericPlayerDTO> finalRankingTableDTO) {
		this.finalRankingTableDTO=finalRankingTableDTO;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayFinalRanking(this.finalRankingTableDTO);
	}

}
