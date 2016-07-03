package server.model.actions.bonusActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonuses.Bonus;
import server.model.gameTable.City;
import server.model.player.Player;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * This class modelizes the action associated to the ChooseCityBonus
 * @author cg31
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

	public List<City> getSelectedCities() {
		return selectedCities;
	}

	/**
	 * Assigns the bonus of city/cities selected to the current player
	 * @return true, if the bonus assignment went well, false otherwise
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
		
		this.updateClients(game);
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateAvailableActions(game);
		
		return true;
	}
	
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableServerNotify(game, new ArrayList<Player>(game.getAllPlayers()), false));
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
	}

	@Override
	public ActionDTO map() {
		return new ChooseCityActionDTO(this.numberOfCities);
	}
	
}
