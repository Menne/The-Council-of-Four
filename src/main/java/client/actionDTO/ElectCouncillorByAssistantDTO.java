package client.actionDTO;

import client.ModelDTO.CardColourDTO;import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.ElectCouncillorByAssistantParser;
import model.Game;
import model.actions.Action;
import model.actions.standardAction.ElectCouncillorByAssistant;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.RegionBoard;

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
		ElectCouncillorByAssistant action =new ElectCouncillorByAssistant();
		
		for(RegionBoard region : game.getGameTable().getRegionBoards())
			if(checkCouncilBalcony(region.getRegionBalcony()))
				action.setCouncilBalcony(region.getRegionBalcony());
		if(checkCouncilBalcony(game.getGameTable().getCouncilOfKing()))
			action.setCouncilBalcony(game.getGameTable().getCouncilOfKing());
		
		for(Councillor councillor : game.getGameTable().getCouncilReserve().getCouncillors())
			if(councillor.getColour().getColour().equals(this.newCouncillor.getName()))
				action.setNewCouncillor(councillor);
			
		return action;
	}
	
	private boolean checkCouncilBalcony(CouncilBalcony realBalcony){
		for(int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			if(!realBalcony.getCouncillors()[i].getColour().getColour().equals(this.councilBalcony[i].getName()))
				return false;
		return true;
	}
}
	
