package modelDTO.clientNotifies;

import java.util.ArrayList;

import client.view.notifies.ClientGameOverNotify;
import modelDTO.GameDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;

public class EndGameDTONotifies implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3067799541565058885L;
	private final ArrayList<GenericPlayerDTO> finalRankingTable;
	
	public EndGameDTONotifies(ArrayList<GenericPlayerDTO> players) {
		this.finalRankingTable=players;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientGameOverNotify(finalRankingTable));
	}

}
