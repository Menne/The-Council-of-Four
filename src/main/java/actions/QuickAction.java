package actions;

import controller.NormalTurn;
import model.Game;

/**
 * The class that models the generic quick actions of the game
 * @author Luca
 *
 */
public abstract class QuickAction extends Action {

	public QuickAction(Game game){
		super(game);
	}
	
	protected void decrementAction(){
		NormalTurn turn=(NormalTurn) this.game.getCurrentTurn();
		turn.decrementQuickActionAvailable();
	}
}