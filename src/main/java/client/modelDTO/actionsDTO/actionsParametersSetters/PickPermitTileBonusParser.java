package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.PickPermitTileActionDTO;
import client.view.ClientView;

public class PickPermitTileBonusParser implements ActionParserVisitor {

	private PickPermitTileActionDTO selectedAction;

	public PickPermitTileBonusParser(PickPermitTileActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public PickPermitTileActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("the name of the region in which you want to pick");
		this.selectedAction.setSelectedRegion(view.askForRegionBoard());

		view.displayMessage("the number of permit tile you want to pick");
		this.selectedAction.setNumberOfPermitTile(view.askForNumberOfPermitTile
				(this.selectedAction.getSelectedRegion()));
		
		this.selectedAction.parametersSetted();
		
		return this.selectedAction;
	}

}
