package modelDTO.actionsDTO;

import modelDTO.GameDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.ElectCouncillorParser;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.RegionBoard;

public class ElectCouncillorDTO implements ActionDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1250515570460488100L;
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
	public Action map(Game game) {
		ElectCouncillor action = new ElectCouncillor();
		
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
	
	
	@Override
	public String toString() {
		return "m1: elect a councillor";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ElectCouncillorParser(this, game);
	}

	
	
}
