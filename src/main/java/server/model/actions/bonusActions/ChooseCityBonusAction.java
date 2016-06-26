package server.model.actions.bonusActions;

import java.util.List;

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

	private List<City> selectedCities;
	
	public void setSelectedCity(List<City> selectedCities) {
		this.selectedCities=selectedCities;
	}
		
	/**
	 * Assigns the bonus/bonuses of the city to the current player
	 */
	@Override
	public boolean executeAction(Game game) {
		if (this.selectedCities.isEmpty()) {
			game.setState(game.getState().moveToNextTransition(game));
			return false;
		}
		
		for (City city : this.selectedCities)
			for (Bonus bonusToAssign : city.getRewardToken().getRewardTokenBonus())
				bonusToAssign.assignBonus(game);
		
		game.setState(game.getState().moveToNextTransition(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		throw new IllegalStateException("ChooseCityBonusAction doesn't require mapping");
	}
	
}
