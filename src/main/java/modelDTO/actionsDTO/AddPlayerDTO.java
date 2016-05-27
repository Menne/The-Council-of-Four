package modelDTO.actionsDTO;

import modelDTO.GameDTO;
import modelDTO.parser.ActionParserVisitor;
import server.model.Game;
import server.model.actions.Action;

public class AddPlayerDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3589948126875889377L;
	private String playerName;
	
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action map(Game game) {
		return null;
	}

	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String toString() {
		return "player added";
	}

}
