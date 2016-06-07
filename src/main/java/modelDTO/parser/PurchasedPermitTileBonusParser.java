package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;

public class PurchasedPermitTileBonusParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO selectedAction;
	private GameDTO game;
	private String currentParameter;

	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
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
		
		if (!this.game.getClientPlayer().getAvailablePermitTiles().isEmpty()) {
			
			this.game.notifyObserver(new ParametersNotify(parser.acceptablePermitTiles(), this));
			this.selectedAction.setPermitTile(parser.permitTileTranslator(currentParameter));
			
			this.selectedAction.parametersSetted();
		}
		
		else 
			this.game.notifyObserver(new ActionNotify
					("But it seems that you haven't any permit tiles in your hand!"));
		
		return this.selectedAction;
		
	}

}
