package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.ElectCouncillorParser;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.view.ClientView;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ElectCouncillorDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1250515570460488100L;
	private CardColourDTO newCouncillor;
	private CardColourDTO[] councilBalcony;
	private boolean parametersSetted=false;

	public CardColourDTO getNewCouncillor() {
		return newCouncillor;
	}

	public CardColourDTO[] getCouncilBalcony() {
		return councilBalcony;
	}
	
	
	public void setNewCouncillor(CardColourDTO newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CardColourDTO[] councilBalcony) {
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
	public ActionParserVisitor setParser(ClientView view, GameDTO game) {
		return new ElectCouncillorParser(this, view, game);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}