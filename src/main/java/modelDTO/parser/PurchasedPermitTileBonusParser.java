package modelDTO.parser;

import client.view.ClientView;
import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;

public class PurchasedPermitTileBonusParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO selectedAction;
	private ClientView view;
	private Object currentParameter;
	private GameDTO game;

	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}

	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Permit tile bonus earned! You have the possibility to choose from your permit tiles,"
						+ "covered or not, and get the bonuses associated to that");
		
		if (!this.game.getClientPlayer().getAvailablePermitTiles().isEmpty()) {
			
			this.selectedAction.setPermitTile(this.view.askForPermitTile
					(this.game.getClientPlayer().getAvailablePermitTiles()));
			
			this.selectedAction.parametersSetted();
		}
		
		else 
			this.view.displayMessage("But it seems that you haven't any permit tiles in your hand!");
		
		return this.selectedAction;
		
	}

}
