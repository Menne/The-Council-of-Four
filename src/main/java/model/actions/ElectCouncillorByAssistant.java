package model.actions;

import model.Game;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.parser.ActionParserVisitor;
import model.parser.ElectCouncillorByAssistantParser;
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
	
	public ElectCouncillorByAssistant(Game game){
		super(game);
	}
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkAssistants(){
		return this.game.getCurrentPlayer().getAssistants()>=necessaryAssistants;
	}
	
	private boolean checkCouncillor(){
		return this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(this.newCouncillor);
	}

	/**
	  * Substitutes a given councillor in one of the balconies of the game,
	  * adds the old substituted councillor in the reserve,
	  * decrement an assistant to the current player.
	  * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() throws NullPointerException{
		if(this.councilBalcony==null || this.newCouncillor==null)
			throw new NullPointerException("Parameters not setted");
		Councillor oldCouncillor;
		if((!this.checkAssistants())||(!this.checkCouncillor())){
			this.sendErrorNotify();
			return false;
		}
		oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		this.game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		this.game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.nextState();
		this.game.notifyObserver(new GameNotify(this.game));
		return true;
	}

	@Override
	public String toString() {
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new ElectCouncillorByAssistantParser(this);
	}

}