package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.view.ClientView;

public class PurchasedPermitTileBonusParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO selectedAction;
	private ClientView view;
	private GameDTO game;

	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
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
