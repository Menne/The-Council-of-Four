package modelDTO.actionsDTO;

import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

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
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("AddPlayerDTO doesn't require mapping");
	}

}
