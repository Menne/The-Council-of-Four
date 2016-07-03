package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.modelDTO.actionsParametersSetters.ElectCouncillorParametersSetter;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import server.model.actions.Action;
import server.model.mappers.ActionMapperVisitor;

/**
 * This class represents the DTO version of the ElectCouncillor action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class ElectCouncillorDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = -1250515570460488100L;
	private CouncillorDTO newCouncillor;
	private CouncillorDTO[] councilBalcony;
	private boolean parametersSetted=false;

	
	public CouncillorDTO getNewCouncillor() {
		return this.newCouncillor;
	}

	public void setNewCouncillor(CouncillorDTO newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public CouncillorDTO[] getCouncilBalcony() {
		return this.councilBalcony;
	}

	public void setCouncilBalcony(CouncillorDTO[] councilBalcony) {
		this.councilBalcony = councilBalcony;
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
		return "m1: elect a councillor";
	}

	@Override
	public ActionParametersSetter setParser() {
		return new ElectCouncillorParametersSetter(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}
