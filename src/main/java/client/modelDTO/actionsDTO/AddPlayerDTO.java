package client.modelDTO.actionsDTO;

import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This object is created when a player inserts his name and is ready for a new game
 * @author cg31
 *
 */
public class AddPlayerDTO implements ActionDTO {

	private static final long serialVersionUID = -3589948126875889377L;
	private final String playerName;
	
	/**
	 * Constructor of AddPlayerDTO
	 * @param playerName is the player name
	 */
	public AddPlayerDTO(String playerName) {
		this.playerName=playerName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("AddPlayerDTO doesn't require mapping");
	}

}
