package client.view.notifies;

import java.util.ArrayList;

import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.view.ClientView;

/**
 * This class contains the logic for notifying the ClientView that the game is ended
 * @author cg31
 *
 */
public class ClientGameOverNotify implements ClientViewNotify {

	private final ArrayList<GenericPlayerDTO> finalRankingTable;
	
	/**
	 * Constructor of ClientGameOverNotify
	 * @param finalRankingTable is the list of players of the game just ended, with all their informations
	 */
	public ClientGameOverNotify(ArrayList<GenericPlayerDTO> finalRankingTable) {
		this.finalRankingTable=finalRankingTable;
	}
	
	@Override
	public void updateView(ClientView view) {
		view.displayFinalRanking(this.finalRankingTable);
	}

}
