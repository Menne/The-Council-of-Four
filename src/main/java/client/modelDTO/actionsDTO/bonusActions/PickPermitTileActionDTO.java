package client.modelDTO.actionsDTO.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters.PickPermitTileBonusParser;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class PickPermitTileActionDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
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
	public ActionParserVisitor setParser() {
		return new PickPermitTileBonusParser(this);
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