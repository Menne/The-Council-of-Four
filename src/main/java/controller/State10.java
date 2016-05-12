package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.AcquirePermitTile;
import model.actions.Action;
import model.actions.BuildByKing;
import model.actions.BuildByPermitTile;
import model.actions.ElectCouncillor;
import model.actions.MainAction;
import model.actions.PickPoliticsCard;

public class State10 implements State{

	public void handleAction(Game game, MainAction action) {
		if(action.executeAction()){
			game.nextPlayer();
			Action pickCard= new PickPoliticsCard(game);
			pickCard.executeAction();
			game.setState(new State11());
			}		
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
