package server.model.actions.bonusActions;

import modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.Emporium;

/**
 * This class models the action associated to the choice of the ChooseCityBonus.
 * @author Emanuele
 *
 */

public class ChooseCityBonusAction implements Action {

	protected Game game;
	private City selectedCity;
		
	/**
	 * Constructor or the ChooseCityBonusAction
	 * @param game is the current game
	 * @param selectedCity is the city selected by the current player
	 */
	public ChooseCityBonusAction(Game game, City selectedCity){
		this.game=game;
		this.selectedCity=selectedCity;
	}
		
	/**
	 * If there is the current player's emporium i the selected city,
	 * assigns the bonus/bonuses of the city to the current player
	 */
	@Override
	public boolean executeAction(Game game) {
			
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
	public ActionDTO map() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
