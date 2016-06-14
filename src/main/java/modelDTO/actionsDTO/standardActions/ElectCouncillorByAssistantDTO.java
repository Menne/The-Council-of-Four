package modelDTO.actionsDTO.standardActions;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.ElectCouncillorByAssistantParser;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ElectCouncillorByAssistantDTO implements ActionDTO, ActionWithParameters {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6230692405420942190L;
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
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionParserVisitor setParser(ClientView view, GameDTO game) {
		return new ElectCouncillorByAssistantParser(this, view, game);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
	
