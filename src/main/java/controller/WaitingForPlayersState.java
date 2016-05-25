package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.AddPlayer;

public class WaitingForPlayersState implements State {

	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions =new ArrayList<Action>();
		acceptableActions.add(new AddPlayer());
		return acceptableActions;
	}
	
	@Override
	public State addPlayerTransition(Game game) {
		if(game.getPlayers().size()==2)
			return new BeginState();
		else
			return this;
	}

	@Override
	public String toString(Game game) {
		return "Waiting for players...";
	}

}
