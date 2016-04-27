package actions;

import model.Game;
/**
 * It's the quick action "engage assistants" it operates on the 
 * protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class EngageAssistant extends QuickAction {

	public EngageAssistant(Game game){
		super(game);
	}

	/**
	 * Decrements 3 coins to the current player and gives him an assistant.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() {
		if(!this.game.getCurrentPlayer().decrementCoins(3))
			return false;		
		this.game.getCurrentPlayer().decrementCoins(4);
		this.game.getCurrentPlayer().incrementAssistants(1);
		return true;
	}

}