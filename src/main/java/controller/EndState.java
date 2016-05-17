package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.MoveToNext;

public class EndState implements State{

	@Override
	public State moveToNextTransition(Game game) {
		
		if(game.getCurrentPlayer().getPlayerNumber()!=game.getPlayers().size())
			return new BeginState();
		return new BeginSellingState();
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new MoveToNext(game));
		return acceptableActions;
	}

	@Override
	public String toString(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
}
