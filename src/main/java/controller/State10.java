package controller;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardAction.AcquirePermitTile;
import server.model.actions.standardAction.BuildByKing;
import server.model.actions.standardAction.BuildByPermitTile;
import server.model.actions.standardAction.ElectCouncillor;

public class State10 implements State{
	
	@Override
	public State mainActionTransition(Game game) {
		
		if(game.getCurrentPlayer().getPlayerNumber()!=game.getPlayers().size()){
			game.nextPlayer();
			return new BeginState();
		}
		else{
			game.startMarket();
			return new SellingState();
		}
	}
		
	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new ElectCouncillor());
		acceptableActions.add(new AcquirePermitTile());
		acceptableActions.add(new BuildByPermitTile());
		acceptableActions.add(new BuildByKing());
		
		return acceptableActions;
	}


	public String toString(Game game) {
		String availableActions = null;
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}

}
