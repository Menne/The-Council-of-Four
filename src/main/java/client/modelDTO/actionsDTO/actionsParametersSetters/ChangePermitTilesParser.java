package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.view.ClientView;

public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTilesDTO selectedAction;

	public ChangePermitTilesParser(ChangePermitTilesDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	
	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to change the permit tiles of a region. Now I need some other infos, like:");
		
		view.displayMessage("the name of the region in which you want to pick");
		this.selectedAction.setSelectedRegion(view.askForRegionBoard());
		
		this.selectedAction.parametersSetted();
		
		return this.selectedAction;
	}

}
