package model.actions;

import model.Game;
/**
 * It's the quick action "engage assistants" it operates on the 
 * protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class EngageAssistant extends QuickAction {

	private static final int necessaryCoins=3;
	
	public EngageAssistant(Game game){
		super(game);
	}
	
	private boolean checkCoins(){
		return this.game.getCurrentPlayer().getCoins()>=necessaryCoins;
	}

	/**
	 * Decrements 3 coins to the current player and gives him an assistant.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() {
		if(!this.checkCoins()){
			this.sendErrorNotify();
			return false;
		}
		this.game.getCurrentPlayer().decrementCoins(necessaryCoins);
		this.game.getCurrentPlayer().incrementAssistants(1);
		return true;
	}

}