package server.model.stateMachine;

import java.util.Arrays;
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
		return Arrays.asList(
				new EngageAssistant(),
				new ChangePermitTiles(),
				new ElectCouncillorByAssistant(),
				new AdditionalMainAction(),
				new MoveToNext());
	}


	public String toString(Game game) {
		String availableActions="";
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}
	
}


