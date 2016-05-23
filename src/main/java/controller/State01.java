package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.MoveToNext;
import model.actions.standardAction.AdditionalMainAction;
import model.actions.standardAction.ChangePermitTiles;
import model.actions.standardAction.ElectCouncillorByAssistant;
import model.actions.standardAction.EngageAssistant;

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


