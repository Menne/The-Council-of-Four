package client.actionDTO;

import model.gameTable.CardColour;

public class ElectCouncillorByAssistantsDTO implements ActionDTO{
		
	private final CardColour newCouncillor;
	private final CardColour[] councilBalcony;
		
		
	public ElectCouncillorByAssistantsDTO(CardColour newCouncillor, CardColour[] councilBalcony){
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
}
	
