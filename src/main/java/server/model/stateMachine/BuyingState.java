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
		game.getMarket().buyingNextPlayer();
		if(game.getMarket().isBuyingPhaseFinished()){
			game.nextPlayer();
			return new BeginState();
		}
		else
			return this;
	}

	@Override
	public State moveToNextTransition(Game game) {
		game.getMarket().buyingNextPlayer();
		if(game.getMarket().isBuyingPhaseFinished()){
			game.nextPlayer();
			return new BeginState();
		}
		else			
			return this;
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new AcceptAnOffer(),new MoveToNext());
	}

}
