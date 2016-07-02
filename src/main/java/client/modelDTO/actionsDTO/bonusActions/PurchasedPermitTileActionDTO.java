package client.modelDTO.actionsDTO.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters.PurchasedPermitTileBonusParser;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This class represents the DTO version of the PurchasedPermitTileAction bonus action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class PurchasedPermitTileActionDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = 893810260862447362L;
	private PermitTileDTO selectedPermitTile;
	private boolean parametersSetted=false;
	
	public void setPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}

	public PermitTileDTO getSelectedPermitTile() {
		return this.selectedPermitTile;
	}

	@Override
	public boolean checkIfParametersSet() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSet() {
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
	
	@Override
	public String toString() {
		return "b3: get bonus!";
	}
	
}
