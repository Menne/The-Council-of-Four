package actions;

import controller.NormalTurn;
import model.Game;

/**
 * The class that models the generic main actions of the game.
 * @author Luca
 *
 */
public abstract class MainAction extends Action {

	public MainAction(Game game){
		super(game);
	}
	
	protected void decrementAction(){
		NormalTurn turn= (NormalTurn) this.game.getCurrentTurn();
		turn.decrementMainActionAvailable();
	}
}
