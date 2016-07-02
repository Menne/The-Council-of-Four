package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a ChangePermitTilesDTO
 * @author cg31
 *
 */
public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTilesDTO selectedAction;

	/**
	 * Constructor of ChangePermitTilesDTO
	 * @param selectedAction is the action selected by the user
	 */
	public ChangePermitTilesParser(ChangePermitTilesDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	
	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to change the permit tiles of a region.");
		
		view.displayMessage("Select the region in which you want to change the permit tiles");
		this.selectedAction.setSelectedRegion(view.askForRegionBoard());
		
		this.selectedAction.parametersSet();
	}

}
