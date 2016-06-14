package modelDTO.actionsDTO.standardActions;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.BuildByPermitTileParser;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class BuildByPermitTileDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8061305769975404856L;
	private  PermitTileDTO selectedPermitTile;
	private  CityDTO selectedCity;
	private boolean parametersSetted=false;

	public PermitTileDTO getSelectedPermitTile() {
		return selectedPermitTile;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile = selectedPermitTile;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}
	
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "m3: build an emporium using a permit tile";
	}

	@Override
	public ActionParserVisitor setParser(ClientView view, GameDTO game) {
		return new BuildByPermitTileParser(this, view, game);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}
