package actions;

import bonus.Bonus;
import gameStuff.City;
import gameStuff.Emporium;
import model.Game;

/**
 * This class models the action associated to the choice of the ChooseCityBonus.
 * @author Emanuele
 *
 */

public class ChooseCityBonusAction extends Action {

	private City selectedCity;
		
	/**
	 * Constructor or the ChooseCityBonusAction
	 * @param game is the current game
	 * @param selectedCity is the city selected by the current player
	 */
	public ChooseCityBonusAction(Game game, City selectedCity){
		super(game);
		this.selectedCity=selectedCity;
	}
		
	/**
	 * If there is the current player's emporium i the selected city,
	 * assigns the bonus/bonuses of the city to the current player
	 */
	public boolean executeAction() {
			
		if (!(CkeckCity()))
			return false;
			
		for (Bonus bonusToAssign : this.selectedCity.getRewardToken())
			bonusToAssign.assignBonus(this.game);
		return true;
	}
		
	/**
	 * Checks if the selected city contains an emporium of the current player
	 * @return TRUE if there is player's emporium; FALSE otherwise
	 */
	private boolean CkeckCity() {
		for (Emporium emporium : this.selectedCity.getCityEmporiums())
			if (emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
				return true;
		return false;
	}

	@Override
	protected void decrementAction() {
		// TODO Auto-generated method stub
		
	}
	
}
