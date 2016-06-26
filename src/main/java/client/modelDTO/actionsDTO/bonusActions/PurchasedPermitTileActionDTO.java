package client.modelDTO.actionsDTO.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters.PurchasedPermitTileBonusParser;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class PurchasedPermitTileActionDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 893810260862447362L;
	private PermitTileDTO selectedPermitTile;
	private boolean parametersSetted=true;
	
	public void setPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}

	public PermitTileDTO getSelectedPermitTile() {
		return selectedPermitTile;
	}

	@Override
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public ActionParserVisitor setParser() {
		return new PurchasedPermitTileBonusParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
