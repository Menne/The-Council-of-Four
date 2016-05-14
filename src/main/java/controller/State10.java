package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.AcquirePermitTile;
import model.actions.Action;
import model.actions.BuildByKing;
import model.actions.BuildByPermitTile;
import model.actions.ElectCouncillor;

public class State10 implements NormalTurnState{
	
	@Override
	public NormalTurnState mainActionTransition() {
		
		return new EndState();
	}


	@Override
	public NormalTurnState quickActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
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
		
		return acceptableActions;
	}





	

	
}
