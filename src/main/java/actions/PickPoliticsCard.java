package actions;

import model.Game;

/**
 * this class extends an abstract action, in particular it 
 * models the first action of a turn, when you pick a politics card
 * @author Emanuele
 *
 */

public class PickPoliticsCard extends Action {
	
	/**
	 * constructor of the action
	 */
	public PickPoliticsCard (Game game){
		super(game);
	}
	
	/**
	 * first you pick a card invoking the method pickCard on the politics deck,
	 * then you add it to the hand of the current player
	 * @return TRUE because the politics deck is never empty and the player's hand never full
	 */
	public boolean executeAction() {
		this.game.getCurrentPlayer().getHand().add(this.game.getGameTable().getPoliticsDeck().pickCard());
		return true;
	}
	
	
}
