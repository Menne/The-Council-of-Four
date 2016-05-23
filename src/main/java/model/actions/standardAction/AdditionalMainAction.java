package model.actions.standardAction;

import model.Game;
import model.actions.QuickAction;
import view.ErrorNotify;
import view.GameNotify;

/**
 * It's the quick action "additional main action" it operates
 * on the protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class AdditionalMainAction extends QuickAction {

	private static final int necessaryAssistants=3;
	
	private boolean checkAssistants(Game game){
		if(game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants)
			return true;
		else
			return false;
	}
	
	/**
	 * Decrements the necessaryAssistants to the current player and give him
	 * an additional main action
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) {
		if(!this.checkAssistants(game)){
			game.notifyObserver(new ErrorNotify("You can't do this action"));
			return false;
		}	
		
		game.setState(game.getState().additionalMainActionTransition());
		game.notifyObserver(new GameNotify(game));
		return true;
	}
	
	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}


}