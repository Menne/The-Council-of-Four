package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.*;

public class State11 implements State {

	@Override
	public State mainActionTransition() {
		
		return new State01();
	}

	@Override
	public State quickActionTransition() {
		
		return new State10();
	}

	@Override
	public State additionalMainActionTransition() throws RuntimeException{

		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State moveToNextTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}
	
	@Override
	public State pickPoliticsCardTransition() throws RuntimeException{
		
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

	public String toString(Game game) {
		String availableActions = "Player"+game.getCurrentPlayer().getName()+
				"is your turn! Your available actions are the following, choose one of them.\n";
		for (Action action : this.getAcceptableActions(game))
			availableActions+= "\n" + action.toString();
		return availableActions;
	}
	
	
	




	
	
	
	
}
