package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.view.ClientView;

public class PurchasedPermitTileBonusParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO selectedAction;

	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}


	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Permit tile bonus earned! You have the possibility to choose from your permit tiles,"
						+ "covered or not, and get the bonuses associated to that");
		
		
		if (!(game.getClientPlayer().getAvailablePermitTiles().isEmpty()
				&& game.getClientPlayer().getCoveredPermitTiles().isEmpty())) {
			
			this.selectedAction.setPermitTile(view.askForPermitTileUncoveredAndCovered());
			
			this.selectedAction.parametersSetted();
		}
		
		else 
			view.displayMessage("But it seems that you haven't any permit tiles in your hand!");
		
		return this.selectedAction;
		
	}

}
