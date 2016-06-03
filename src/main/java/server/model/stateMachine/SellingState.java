package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.MakeAnOffer;

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

}
