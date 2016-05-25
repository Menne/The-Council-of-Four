package controller;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.*;
import server.model.actions.standardAction.AcquirePermitTile;
import server.model.actions.standardAction.BuildByKing;
import server.model.actions.standardAction.BuildByPermitTile;
import server.model.actions.standardAction.ChangePermitTiles;
import server.model.actions.standardAction.ElectCouncillor;
import server.model.actions.standardAction.ElectCouncillorByAssistant;
import server.model.actions.standardAction.EngageAssistant;

public class State11 implements State {

	@Override
	public State mainActionTransition(Game game) {
		
		return new State01();
	}

	@Override
	public State quickActionTransition(Game game) {
		
		return new State10();
	}

	
	
	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new ElectCouncillor());
		acceptableActions.add(new AcquirePermitTile());
		acceptableActions.add(new BuildByPermitTile());
		acceptableActions.add(new BuildByKing());
		acceptableActions.add(new EngageAssistant());
		acceptableActions.add(new ChangePermitTiles());
		acceptableActions.add(new ElectCouncillorByAssistant());
		return acceptableActions;
	}

	public String toString(Game game) {
		String availableActions = "Player"+game.getCurrentPlayer().getPlayerNumber()+
				"is your turn! Your available actions are the following, choose one of them.\n";
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}

}
