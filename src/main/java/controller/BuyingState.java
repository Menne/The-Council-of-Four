package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public class BuyingState implements State {

	@Override
	public State buyActionTransition(Game game) {
		if(game.getMarket().isBuyingPhaseFinished()){
			game.nextPlayer();
			return new BeginState();
		}
		else{
			game.getMarket().buyingNextPlayer();
			return this;
		}
	}

	@Override
	public State moveToNextTransition(Game game) {
		if(game.getMarket().isBuyingPhaseFinished()){
			game.nextPlayer();
			return new BeginState();
		}
		else{
			game.getMarket().buyingNextPlayer();
			return this;
		}
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
