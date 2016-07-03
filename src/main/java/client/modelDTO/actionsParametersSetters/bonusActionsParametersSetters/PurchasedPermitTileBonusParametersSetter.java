package client.modelDTO.actionsParametersSetters.bonusActionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a PurchasedPermitTileActionDTO
 * @author cg31
 *
 */
public class PurchasedPermitTileBonusParametersSetter implements ActionParametersSetter {

	private PurchasedPermitTileActionDTO selectedAction;

	/**
	 * Constructor of PurchasedPermitTileActionDTO
	 * @param selectedAction is the action selected by the user
	 */
	public PurchasedPermitTileBonusParametersSetter(PurchasedPermitTileActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Select the permit tile from which you want to get the bonus");
		this.selectedAction.setPermitTile(view.askForPermitTileUncoveredAndCovered());
		this.selectedAction.parametersSet();
	}

}
