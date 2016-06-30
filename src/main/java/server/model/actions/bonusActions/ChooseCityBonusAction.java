package server.model.actions.bonusActions;

import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.view.notifies.MessageNotify;
import server.view.notifies.PlayerNotify;

/**
 * This class models the action associated to the choice of the ChooseCityBonus.
 * @author Emanuele
 *
 */

public class ChooseCityBonusAction implements Action {

	private List<City> selectedCities;
	private final int numberOfCities;
	
	public ChooseCityBonusAction(int numberOfCities) {
		this.numberOfCities=numberOfCities;
	}
	
	
	public int getNumberOfCities() {
		return this.numberOfCities;
	}

	public void setSelectedCity(List<City> selectedCities) {
		this.selectedCities=selectedCities;
	}
		
	/**
	 * Assigns the bonus/bonuses of the city to the current player
	 */
	@Override
	public boolean executeAction(Game game) {
		if (this.selectedCities==null) {
			game.setState(game.getState().moveToNextTransition(game));
			return false;
		}
		
		for (City city : this.selectedCities)
			for (Bonus bonusToAssign : city.getRewardToken().getRewardTokenBonus())
				bonusToAssign.assignBonus(game);
		
		this.notifyPlayers(game);
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateClients(game);
		
		return true;
	}
	
	private void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageNotify("Bonus earned successfully!", 
				Arrays.asList(game.getCurrentPlayer())));
	}

	@Override
	public ActionDTO map() {
		return new ChooseCityActionDTO(this.numberOfCities);
	}
	
}
