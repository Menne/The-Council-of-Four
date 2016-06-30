package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.PickPoliticsCard;
import server.model.player.Player;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;

/**
 * In this state the player can just pick a politics card
 * @author Luca Scannapieco
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
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers()),false));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", it's your turn! Pick a politics card pressing pc"));
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

	
}
