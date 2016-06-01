package modelDTO.actionsDTO;

import server.model.Game;
import server.model.actions.Action;

public class AddPlayerDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3589948126875889377L;
	private String playerName;
	

	@Override
	public Action map(Game game) {
		return null;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName=playerName;
	}

}
