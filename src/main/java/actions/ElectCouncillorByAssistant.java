package actions;

import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import model.Game;

public class ElectCouncillorByAssistant extends QuickAction {

	private final Councillor newCouncillor;
	private final CouncilBalcony councilBalcony;
	
	public ElectCouncillorByAssistant(Game game, Councillor newCouncillor, CouncilBalcony councilBalcony){
		super(game);
		this.newCouncillor=newCouncillor;
		this.councilBalcony=councilBalcony;
	}
	
	public boolean executeAction() {
		Councillor oldCouncillor;
		if((!this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(newCouncillor))||
				(!this.game.getCurrentPlayer().decrementAssistants(1)))
			return false;
		oldCouncillor=this.councilBalcony.substituteCouncillor(newCouncillor);
		this.game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		this.game.getCurrentPlayer().decrementAssistants(1);
		return true;
	}

}