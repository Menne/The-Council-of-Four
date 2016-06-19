package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.ElectCouncillorParser;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ElectCouncillorDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1250515570460488100L;
	private CouncillorDTO newCouncillor;
	private CouncillorDTO[] councilBalcony;
	private boolean parametersSetted=false;

	
	public CouncillorDTO getNewCouncillor() {
		return newCouncillor;
	}

	public void setNewCouncillor(CouncillorDTO newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public CouncillorDTO[] getCouncilBalcony() {
		return councilBalcony;
	}

	public void setCouncilBalcony(CouncillorDTO[] councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}

	
	@Override
	public String toString() {
		return "m1: elect a councillor";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new ElectCouncillorParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}
