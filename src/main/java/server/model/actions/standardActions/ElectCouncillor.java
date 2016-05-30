package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ElectCouncillorDTO;
import players.Player;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * It's the main action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillor extends MainAction {

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	private static final int givenCoins=4;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkCouncillor(Game game) {
		return game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(this.newCouncillor);
	}
	
	/**
	 * Substitutes a given councillor in one of the balconies of the game,
	 * adds the old substituted councillor in the reserve,
	 * gives to the current player 4 coins.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) throws NullPointerException{
		if(this.newCouncillor==null || this.councilBalcony==null)
			throw new NullPointerException("Parameters not setted");
		
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(game.getCurrentPlayer());
		
		Councillor oldCouncillor;
		if(!this.checkCouncillor(game)){
			this.sendErrorNotify(game,interestedPlayers);
			return false;
		}
		oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		game.getCurrentPlayer().incrementCoins(givenCoins);
		
		this.nextState(game);

		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new AvailableActionsNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		game.notifyObserver(new PlayerNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		
		return true;
	}

	@Override
	public String toString() {
		return "m1: elect a councillor";
	}

	@Override
	public ActionDTO map() {
		return new ElectCouncillorDTO();
	}


}