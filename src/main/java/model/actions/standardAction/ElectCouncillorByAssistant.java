package model.actions.standardAction;

import client.actionDTO.NeedParameters;
import client.actionDTO.ActionDTO;
import client.actionDTO.ElectCouncillorByAssistantDTO;
import model.Game;
import model.actions.QuickAction;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import view.GameNotify;

/**
 * It's the quick action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillorByAssistant extends QuickAction implements NeedParameters{

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	public static final int necessaryAssistants=1;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkAssistants(Game game){
		return game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants;
	}
	
	private boolean checkCouncillor(Game game){
		return game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(this.newCouncillor);
	}

	/**
	  * Substitutes a given councillor in one of the balconies of the game,
	  * adds the old substituted councillor in the reserve,
	  * decrement an assistant to the current player.
	  * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) throws NullPointerException{
		if(this.councilBalcony==null || this.newCouncillor==null)
			throw new NullPointerException("Parameters not setted");
		Councillor oldCouncillor;
		if((!this.checkAssistants(game))||(!this.checkCouncillor(game))){
			this.sendErrorNotify(game);
			return false;
		}
		oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.nextState(game);
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	@Override
	public String toString() {
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionDTO map() {
		return new ElectCouncillorByAssistantDTO();
	}

}