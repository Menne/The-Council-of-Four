package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.*;

public class State11 implements NormalTurnState {

	@Override
	public NormalTurnState mainActionTransition() {
		
		return new State01();
	}

	@Override
	public NormalTurnState quickActionTransition() {
		
		return new State10();
	}

	@Override
	public NormalTurnState additionalMainActionTransition() throws RuntimeException{

		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public NormalTurnState moveToNextTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}
	
	@Override
	public NormalTurnState pickPoliticsCardTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}


	
	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new ElectCouncillor(game));
		acceptableActions.add(new AcquirePermitTile(game));
		acceptableActions.add(new BuildByPermitTile(game));
		acceptableActions.add(new BuildByKing(game));
		acceptableActions.add(new EngageAssistant(game));
		acceptableActions.add(new ChangePermitTiles(game));
		acceptableActions.add(new ElectCouncillorByAssistant(game)); 
		
		return acceptableActions;
	}





	
	
	
	
}
