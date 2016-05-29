package server.model.stateMachine;

import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.*;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;

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
		return Arrays.asList(
				new ElectCouncillor(),
				new AcquirePermitTile(),
				new BuildByPermitTile(),
				new BuildByKing(),
				new EngageAssistant(),
				new ChangePermitTiles(),
				new ElectCouncillorByAssistant());
	}

	@Override
	public String toString(Game game) {
		String availableActions = "Player"+game.getCurrentPlayer().getPlayerNumber()+
				"is your turn! Your available actions are the following, choose one of them.\n";
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}

}
