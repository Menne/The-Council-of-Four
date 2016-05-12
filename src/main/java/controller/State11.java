package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.*;

public class State11 implements State {

	public void handleAction(Game game, MainAction action) {
		if(action.executeAction())
			game.setState(new State01());
	}


	public void handleAction(Game game, QuickAction action) {
		if(action.executeAction())
			game.setState(new State10());		
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
	
	
/*	public void handleAction(GameLogic gameLogic, AdditionalMainActionBonus action){
		if(action.executeAction())
			gameLogic.setState(new state);
	}*/
}
