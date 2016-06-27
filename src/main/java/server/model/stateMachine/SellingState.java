package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.player.Player;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.MarketNotify;
import server.view.notifies.PlayerNotify;

public class SellingState implements State {

	@Override
	public State sellActionTransition(Game game) {
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
		game.notifyObserver(new AvailableActionsNotify(Arrays.asList(), 
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
	public void updateClients(Game game) {
		if (game.getCurrentPlayer().getPlayerNumber()==1) {
			game.notifyObserver(new GameTableNotify
					(game, new ArrayList<Player>(game.getPlayers()), false));
			game.notifyObserver(new MarketNotify(game, 
					new ArrayList<Player>(game.getPlayers()), true, false));
		}
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
					Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MarketNotify(game, 
				new ArrayList<Player>(game.getPlayers()), false, false));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you are in the market phase now. Do you want to sell something to other players?"));
	}

}
