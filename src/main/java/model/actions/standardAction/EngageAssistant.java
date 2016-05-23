package model.actions.standardAction;

import model.Game;
import model.actions.QuickAction;
import view.GameNotify;
/**
 * It's the quick action "engage assistants" it operates on the 
 * protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class EngageAssistant extends QuickAction {

	private static final int necessaryCoins=3;

	
	private boolean checkCoins(Game game){
		return game.getCurrentPlayer().getCoins()>=necessaryCoins;
	}

	/**
	 * Decrements 3 coins to the current player and gives him an assistant.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) {
		if(!this.checkCoins(game)){
			this.sendErrorNotify(game);
			return false;
		}
		game.getCurrentPlayer().decrementCoins(necessaryCoins);
		game.getCurrentPlayer().incrementAssistants(1);
		
		this.nextState(game);
		game.notifyObserver(new GameNotify(game));
		return true;
	}
	
	@Override
	public String toString() {
		return "q1: engage an assistant";
	}
}