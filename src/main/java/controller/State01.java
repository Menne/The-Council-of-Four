package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.AdditionalMainAction;
import model.actions.ChangePermitTiles;
import model.actions.ElectCouncillorByAssistant;
import model.actions.EngageAssistant;
import model.actions.PickPoliticsCard;
import model.actions.QuickAction;

public class State01 implements State {

	public void handleAction(Game game, QuickAction action) {
		if(action.executeAction()){			
			game.nextPlayer();
			Action pickCard= new PickPoliticsCard(game);
			pickCard.executeAction();
			game.setState(new State11());
		}		
	}
	
	
	public void handleAction(Game game, AdditionalMainAction action) {
		if(action.executeAction())
			game.setState(new State10());		
	}


	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions=new ArrayList<Action>();
		acceptableActions.add(new EngageAssistant(game));
		acceptableActions.add(new ChangePermitTiles(game));
		acceptableActions.add(new ElectCouncillorByAssistant(game));
		acceptableActions.add(new AdditionalMainAction(game));		
		return acceptableActions;
	}
}


