package server.model.stateMachine;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.standardActions.AdditionalMainAction;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;

public class State01 implements State {

	@Override
	public State quickActionTransition(Game game) {
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
	public State additionalMainActionTransition() {
		
		return new State10();
	}


	@Override
	public State moveToNextTransition(Game game) {
		
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
		acceptableActions.add(new EngageAssistant());
		acceptableActions.add(new ChangePermitTiles());
		acceptableActions.add(new ElectCouncillorByAssistant());
		acceptableActions.add(new AdditionalMainAction());
		acceptableActions.add(new MoveToNext());
		return acceptableActions;
	}


	public String toString(Game game) {
		String availableActions="";
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}
	
}


