package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.PickPoliticsCard;
import server.model.player.Player;
import server.serverNotifies.AvailableActionsServerNotify;
import server.serverNotifies.GameTableServerNotify;

/**
 * In this state the player can just pick a politics card
 * @author cg31
 *
 */
public class BeginState implements State {
	
	@Override
	public State pickPoliticsCardTransition() {
		return new State11();
	}	
	

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new PickPoliticsCard());
	}

	@Override
	public State moveToNextTransition(Game game) {
		if (!game.getCurrentPlayer().equals(game.lastPlayer())){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.nextPlayer();
			game.startMarket();
			return new SellingState();
		}
	}
	
	@Override
	public void updateAvailableActions(Game game) {
		game.notifyObserver(new GameTableServerNotify(game, new ArrayList<Player>(game.getAllPlayers()), false));
		game.notifyObserver(new AvailableActionsServerNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", it's your turn! First of all, you should pick a politics card"));
	}

	
}
