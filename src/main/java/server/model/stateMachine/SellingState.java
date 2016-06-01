package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.MakeAnOffer;

public class SellingState implements State {

	@Override
	public State sellActionTransition() {
		
		return this;
	}

	@Override
	public State moveToNextTransition(Game game) {
		game.getMarket().sellingNextPlayer();
		if(game.getMarket().isSellingPhaseFinished()){
			game.nextPlayer();
			return new BuyingState();
		}			
		else
			return this;
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new MakeAnOffer(), new MoveToNext());
	}

}
