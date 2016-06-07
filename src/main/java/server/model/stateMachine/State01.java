package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import players.Player;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.standardActions.AdditionalMainAction;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

public class State01 implements State {

	@Override
	public State quickActionTransition(Game game) {
		game.notifyObserver(new AvailableActionsNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, your turn is over now. I will notify you when it will be your turn again"));
		if (game.getCurrentPlayer().getPlayerNumber()!=game.getPlayers().size()+game.getQuittedPlayers().size()){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.nextPlayer();
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
		game.notifyObserver(new AvailableActionsNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, I will notify you when it will be your turn again"));
		if (game.getCurrentPlayer().getPlayerNumber()!=game.getPlayers().size()+game.getQuittedPlayers().size()){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.nextPlayer();
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


	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you have the following available actions. Choose one of them, or, if you want to finish the turn, press sk"));
	}
	
}


