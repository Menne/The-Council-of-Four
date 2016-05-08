 package actions;

import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import model.Game;

/**
 * It's the main action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillor extends MainAction implements NeedParameters{

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	private static final int givenCoins=4;
	
	public ElectCouncillor(Game game){
		super(game);
	}
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkCouncillor() {
		return this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(this.newCouncillor);
	}
	
	/**
	 * Substitutes a given councillor in one of the balconies of the game,
	 * adds the old substituted councillor in the reserve,
	 * gives to the current player 4 coins.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() throws NullPointerException{
		if(this.newCouncillor==null || this.councilBalcony==null)
			throw new NullPointerException("Parameters not setted");
		Councillor oldCouncillor;
		if(!this.checkCouncillor())
			return false;
		oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		this.game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		this.game.getCurrentPlayer().incrementCoins(givenCoins);
		this.decrementAction();
		return true;
	}


}