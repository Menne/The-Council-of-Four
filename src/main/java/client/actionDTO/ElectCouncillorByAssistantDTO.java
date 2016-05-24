package client.actionDTO;

import client.ModelDTO.CardColourDTO;
import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.ElectCouncillorByAssistantParser;
import model.Game;
import model.actions.Action;
import model.gameTable.CardColour;

public class ElectCouncillorByAssistantDTO implements ActionDTO{
		
	private CardColourDTO newCouncillor;
	private CardColourDTO[] councilBalcony;


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

	@Override
	public String toString() {
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ElectCouncillorByAssistantParser(this, game);
	}

	@Override
	public Action map(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
