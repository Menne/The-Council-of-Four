package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.ChangePermitTilesParser;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ChangePermitTilesDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3441345313935469077L;
	private RegionDTO selectedRegion;
	private boolean parametersSetted=false;


	public RegionDTO getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(RegionDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	@Override
	public boolean checkIfParametersSet() {
		return parametersSetted;
	}
	@Override
	public void parametersSet() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "q2: change the permit tiles of a region";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new ChangePermitTilesParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}
