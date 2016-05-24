package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.ElectCouncillorByAssistantParser;
import model.gameTable.CardColour;

public class ElectCouncillorByAssistantDTO implements ActionDTO{
		
	private final CardColour newCouncillor;
	private final CardColour[] councilBalcony;
		
		
	public ElectCouncillorByAssistantDTO(CardColour newCouncillor, CardColour[] councilBalcony){
		this.newCouncillor=newCouncillor;
		this.councilBalcony=councilBalcony;
	}

	public CardColour getNewCouncillor() {
		return newCouncillor;
	}

	public CardColour[] getCouncilBalcony() {
		return councilBalcony;
	}

	@Override
	public String toString() {
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ElectCouncillorByAssistantParser(this, game);
	}
}
	
