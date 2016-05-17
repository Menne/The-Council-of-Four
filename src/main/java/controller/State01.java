package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.AdditionalMainAction;
import model.actions.ChangePermitTiles;
import model.actions.ElectCouncillorByAssistant;
import model.actions.EngageAssistant;
import model.actions.MoveToNext;

public class State01 implements State {

	@Override
	public State quickActionTransition() {
		
		return new EndState();
	}


	@Override
	public State additionalMainActionTransition() {
		
		return new State10();
	}


	@Override
	public State moveToNextTransition(Game game) {
		
		if(game.getCurrentPlayer().getPlayerNumber()!=game.getPlayers().size())
			return new BeginState();
		return new BeginSellingState();
	}
	
	
	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new EngageAssistant(game));
		acceptableActions.add(new ChangePermitTiles(game));
		acceptableActions.add(new ElectCouncillorByAssistant(game));
		acceptableActions.add(new AdditionalMainAction(game));
		acceptableActions.add(new MoveToNext(game));
		return acceptableActions;
	}


	public String toString(Game game) {
		String availableActions = null;
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}
	
}


