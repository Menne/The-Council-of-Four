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

public class State01 implements NormalTurnState {

	@Override
	public NormalTurnState mainActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}


	@Override
	public NormalTurnState quickActionTransition() {
		
		return new EndState();
	}


	@Override
	public NormalTurnState additionalMainActionTransition() {
		
		return new State10();
	}


	@Override
	public NormalTurnState moveToNextTransition() {
		
		return new BeginState();
	}

	@Override
	public NormalTurnState pickPoliticsCardTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
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





	


	
}


