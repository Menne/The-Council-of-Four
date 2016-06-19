package server.model.actions.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.City;

/**
 * This class models the action associated to the choice of the ChooseCityBonus.
 * @author Emanuele
 *
 */

public class ChooseCityBonusAction implements Action {

	private City selectedCity;
	
	public void setSelectedCity(City selectedCity) {
		this.selectedCity=selectedCity;
	}
		
	/**
	 * Assigns the bonus/bonuses of the city to the current player
	 */
	@Override
	public boolean executeAction(Game game) {
			
		if(this.selectedCity==null)
			throw new NullPointerException("you have to select a city for this action");
		for (Bonus bonusToAssign : this.selectedCity.getRewardToken().getRewardTokenBonus())
			bonusToAssign.assignBonus(game);
		
		game.setState(game.getState().moveToNextTransition(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		throw new IllegalStateException("ChooseCityBonusAction doesn't require mapping");
	}
	
}
