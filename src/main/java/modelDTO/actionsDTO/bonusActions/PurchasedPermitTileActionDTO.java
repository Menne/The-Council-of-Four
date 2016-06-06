package modelDTO.actionsDTO.bonusActions;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.PurchasedPermitTileParser;
import server.model.Game;
import server.model.actions.Action;

public class PurchasedPermitTileActionDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 893810260862447362L;

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new PurchasedPermitTileParser(this, game);
	}

	@Override
	public boolean checkIfParametersSetted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void parametersSetted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action map(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

}
