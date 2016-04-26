 package actions;

import gameStuff.Councillor;
import model.Game;
/**
 * This class is the main action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction
 * @author Luca
 *
 */
public class ElectCouncillor extends MainAction {

	public ElectCouncillor(Game game){
		super(game);
	}
/**
 * Substitute a given councillor in one of the regions.
 * @param newCouncillor The councillor to add on the balcony
 * @param regionIndex The index of the region where we want substitute the councillor.
 * It's negative if we want to select the councilOfKing.
 * @return TRUE the action ends well; FALSE otherwise.
 */
	public boolean executeAction(Councillor newCouncillor, int regionIndex) {
		Councillor oldCouncillor;
		if(!this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(newCouncillor))
			return false;
		if(regionIndex<0){
			oldCouncillor=this.game.getGameTable().getCouncilOfKing().
					substituteCouncillor(newCouncillor);
			this.game.getGameTable().getCouncilReserve().
					getCouncillors().add(oldCouncillor);
		}
		else{
			oldCouncillor=this.game.getGameTable().getRegionBoards().get(regionIndex)
				.getRegionBalcony().substituteCouncillor(newCouncillor);
			this.game.getGameTable().getCouncilReserve().getCouncillors().
				add(oldCouncillor);
		}
		this.game.getCurrentPlayer().incrementCoins(4);
		return true;
	}

}