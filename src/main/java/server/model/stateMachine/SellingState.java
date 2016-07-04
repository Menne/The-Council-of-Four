package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.player.Player;
import server.serverNotifies.AvailableActionsServerNotify;
import server.serverNotifies.MarketServerNotify;

/**
 * Models the market state in the current player can make an offer 
 * @author cg31
 *
 */
public class SellingState implements State {

	@Override
	public State sellActionTransition(Game game) {
		game.notifyObserver(new AvailableActionsServerNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, your turn is over now. I will notify you when it will be your turn again"));
		if (!game.getMarket().getSellingPlayerList().isEmpty()){
			game.getMarket().sellingNextPlayer(game);
			return this;
		}			
		else {
			game.getMarket().buyingNextPlayer(game);
			return new BuyingState();
		}
	}

	@Override
	public State moveToNextTransition(Game game) {
		game.notifyObserver(new AvailableActionsServerNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, I will notify you when it will be your turn again"));
		if (!game.getMarket().getSellingPlayerList().isEmpty()){
			game.getMarket().sellingNextPlayer(game);
			return this;
		}			
		else {
			game.getMarket().buyingNextPlayer(game);
			return new BuyingState();
		}
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new MakeAnOffer(), new MoveToNext());
	}

	@Override
	public void updateAvailableActions(Game game) {
		if (game.getCurrentPlayer().equals(game.firstPlayer())) {
			game.notifyObserver(new MarketServerNotify(game, 
					new ArrayList<Player>(game.getAllPlayers()), true, false));
			game.notifyObserver(new MarketServerNotify(game, 
					new ArrayList<Player>(game.getAllPlayers()), false, false));
		}
		game.notifyObserver(new AvailableActionsServerNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you are in the market phase now. Do you want to sell something to other players?"));
	}

}
