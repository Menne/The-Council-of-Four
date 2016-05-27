package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.PickPoliticsCard;

public class BeginState implements State {
	
	@Override
	public State pickPoliticsCardTransition() {
		return new State11();
	}	
	

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new PickPoliticsCard());
	}

	
	public String toString(Game game) {
		String availableActions = "Player "+game.getCurrentPlayer().getPlayerNumber()+
				" is your turn! Pick a politics card pressing pc.\n";
		return availableActions;
	}

}
