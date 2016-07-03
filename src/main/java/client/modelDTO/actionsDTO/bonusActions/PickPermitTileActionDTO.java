package client.modelDTO.actionsDTO.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.modelDTO.actionsParametersSetters.bonusActionsParametersSetters.PickPermitTileBonusParametersSetter;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.actions.Action;
import server.model.mappers.ActionMapperVisitor;

/**
 * This class represents the DTO version of the PickPermitTileAction bonus action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class PickPermitTileActionDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = 893810260862447362L;
	private RegionDTO selectedRegion;
	private Integer numberOfPermitTile;
	private boolean parametersSetted=false;
	
	
	public int getNumberOfPermitTiles() {
		return this.numberOfPermitTile;
	}

	public RegionDTO getSelectedRegion() {
		return this.selectedRegion;
	}
	
	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile=numberOfPermitTile;
	}

	public void setSelectedRegion(RegionDTO selectedRegion) {
		this.selectedRegion=selectedRegion;
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
	public ActionParametersSetter setParser() {
		return new PickPermitTileBonusParametersSetter(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	@Override
	public String toString() {
		return "b2: get bonus!";
	}

}