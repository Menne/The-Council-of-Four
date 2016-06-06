package modelDTO.parser;

import client.view.notifies.ActionNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;

public class PurchasedPermitTileParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO purchasedPermitTileActionDTO;
	private GameDTO game;
	private String currentParameter;

	public PurchasedPermitTileParser(PurchasedPermitTileActionDTO purchasedPermitTileActionDTO, GameDTO game) {
		this.purchasedPermitTileActionDTO=purchasedPermitTileActionDTO;
		this.game=game;
	}

	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	@Override
	public ActionDTO setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("Permit tile bonus earned! You have the possibility to choose from your permit tiles,"
						+ "covered or not, and get the bonuses associated to that"));
		parser.acceptablePermitTiles();
		
		return this.purchasedPermitTileActionDTO;
		
	}

}
