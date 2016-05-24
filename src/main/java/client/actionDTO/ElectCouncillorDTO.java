package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.ElectCouncillorParser;
import model.gameTable.CardColour;

public class ElectCouncillorDTO implements ActionDTO {
	
	private final CardColour newCouncillor;
	private final CardColour[] councilBalcony;
	
	public ElectCouncillorDTO(CardColour newCouncillor, CardColour[] councilBalcony){
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
		return "m1: elect a councillor";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ElectCouncillorParser(this, game);
	}
	
	
}
