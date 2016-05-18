package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public class SellingState implements State {

	@Override
	public State sellActionTransition() {
		
		return this;
	}

	@Override
	public State moveToNextTransition(Game game) {
		if(game.getMarket().isSellingPhaseFinished()){
			game.nextPlayer();
			return new BuyingState();
		}			
		else{
			game.getMarket().sellingNextPlayer();
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
