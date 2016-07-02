package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.BuildByPermitTileParser;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This class represents the DTO version of the BuildByPermitTile action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class BuildByPermitTileDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = -8061305769975404856L;
	private PermitTileDTO selectedPermitTile;
	private CityDTO selectedCity;
	private boolean parametersSetted=false;

	public PermitTileDTO getSelectedPermitTile() {
		return this.selectedPermitTile;
	}

	public CityDTO getSelectedCity() {
		return this.selectedCity;
	}

	public void setSelectedPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile = selectedPermitTile;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
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
	public String toString() {
		return "m3: build an emporium using a permit tile";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new BuildByPermitTileParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}
