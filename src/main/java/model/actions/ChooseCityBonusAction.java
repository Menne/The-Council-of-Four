package model.actions;

import model.Game;
import model.bonus.Bonus;
import model.gameTable.City;
import model.gameTable.Emporium;
import model.parser.ActionParserVisitor;

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
	public boolean executeAction(Game game) {
			
		if (!(CkeckCity(game)))
			return false;
			
		for (Bonus bonusToAssign : this.selectedCity.getRewardToken())
			bonusToAssign.assignBonus(this.game);
		return true;
	}
		
	/**
	 * Checks if the selected city contains an emporium of the current player
	 * @return TRUE if there is player's emporium; FALSE otherwise
	 */
	private boolean CkeckCity(Game game) {
		for (Emporium emporium : this.selectedCity.getCityEmporiums())
			if (emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
				return true;
		return false;
	}

	@Override
	public ActionParserVisitor setParser(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
