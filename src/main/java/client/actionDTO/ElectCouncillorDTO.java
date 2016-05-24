package client.actionDTO;

import client.ModelDTO.CardColourDTO;
import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.ElectCouncillorParser;
import model.Game;
import model.actions.Action;
import model.gameTable.CardColour;

public class ElectCouncillorDTO implements ActionDTO {
	
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
		return "m1: elect a councillor";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ElectCouncillorParser(this, game);
	}

	@Override
	public Action map(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
