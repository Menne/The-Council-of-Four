package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.ElectCouncillorByAssistantParser;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This class represents the DTO version of the ElectCouncillorByAssistant action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class ElectCouncillorByAssistantDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = -6230692405420942190L;
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
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new ElectCouncillorByAssistantParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
	
