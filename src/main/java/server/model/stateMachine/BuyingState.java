package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.AcceptAnOffer;

public class BuyingState implements State {

	@Override
	public State buyActionTransition(Game game) {
		if (!game.getMarket().getBuyingPlayerList().isEmpty()){
			game.getMarket().buyingNextPlayer(game);
			return this;
		}
		else {
			game.nextPlayer();
			return new BeginState();
		}
	}

	@Override
	public State moveToNextTransition(Game game) {
		if (!game.getMarket().getBuyingPlayerList().isEmpty()){
			game.getMarket().buyingNextPlayer(game);
			return this;
		}
		else {
			game.nextPlayer();
			System.out.println(game.getCurrentPlayer().getName());
			return new BeginState();
		}
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new AcceptAnOffer(),new MoveToNext());
	}

}
