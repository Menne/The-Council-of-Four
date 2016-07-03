package client.modelDTO.actionsParametersSetters.bonusActionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a PickPermitTileActionDTO
 * @author cg31
 *
 */
public class PickPermitTileBonusParametersSetter implements ActionParametersSetter {

	private PickPermitTileActionDTO selectedAction;

	/**
	 * Constructor of AcceptAnOfferDTO
	 * @param selectedAction is the action selected by the user
	 */
	public PickPermitTileBonusParametersSetter(PickPermitTileActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Select the region in which you want to pick the permit tile");
		this.selectedAction.setSelectedRegion(view.askForRegionBoard());

		view.displayMessage("Select the permit tile you want to pick");
		this.selectedAction.setNumberOfPermitTile(view.askForNumberOfPermitTile
				(this.selectedAction.getSelectedRegion()));
		
		this.selectedAction.parametersSet();
	}

}
