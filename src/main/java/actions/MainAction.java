package actions;

import controller.NormalTurn;
import model.Game;

/**
 * The class that models the generic main actions of the game.
 * @author Luca
 *
 */
public abstract class MainAction implements Action {

	protected final Game game;
	
	public MainAction(Game game){
		this.game=game;
	}
	
	public void decrementAction(){
		NormalTurn turn= (NormalTurn) this.game.getCurrentTurn();
		turn.decrementMainActionAvailable();
	}
}
