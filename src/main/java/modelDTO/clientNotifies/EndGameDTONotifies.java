package modelDTO.clientNotifies;

import java.util.ArrayList;

import modelDTO.GameDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;

public class EndGameDTONotifies implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3067799541565058885L;
	private final ArrayList<GenericPlayerDTO> players;
	
	public EndGameDTONotifies(ArrayList<GenericPlayerDTO> players) {
		this.players=players;
	}
	
	public ArrayList<GenericPlayerDTO> getPlayers() {
		return players;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		return;

	}

}
