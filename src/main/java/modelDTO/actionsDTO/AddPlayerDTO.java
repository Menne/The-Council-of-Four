package modelDTO.actionsDTO;

import server.model.actions.Action;
import server.view.mapperVisitor.ActionDTOMapper;

public class AddPlayerDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3589948126875889377L;
	private String playerName;
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName=playerName;
	}

	@Override
	public Action startVisitor(ActionDTOMapper mapper) {
		return null;
	}

}
