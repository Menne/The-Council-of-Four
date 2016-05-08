package actions;

import controller.NormalTurn;
import model.Game;

/**
 * The class that models the generic quick actions of the game
 * @author Luca
 *
 */
public abstract class QuickAction implements Action {

	protected Game game;
	
	public QuickAction(Game game){
		this.game=game;
	}
	
	public void decrementAction(){
		NormalTurn turn=(NormalTurn) this.game.getCurrentTurn();
		turn.decrementQuickActionAvailable();
	}
}