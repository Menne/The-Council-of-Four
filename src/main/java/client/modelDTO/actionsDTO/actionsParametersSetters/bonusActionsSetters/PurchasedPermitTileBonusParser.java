package client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
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
			this.selectedAction.setPermitTile(view.askForPermitTileUncoveredAndCovered());
		this.selectedAction.parametersSetted();
		return this.selectedAction;
	}

}
