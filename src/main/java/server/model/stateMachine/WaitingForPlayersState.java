package server.model.stateMachine;

import java.util.ArrayList;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.AddPlayer;
import server.view.notifies.GameNotify;

public class WaitingForPlayersState implements State {

	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableActions =new ArrayList<Action>();
		acceptableActions.add(new AddPlayer());
		return acceptableActions;
	}
	
	@Override
	public void addPlayerTransition(Game game) {
		if(game.getPlayers().size()==2){
			game.setCurrentPlayer(game.getPlayers().get(0));
			game.setState(new BeginState());
			game.notifyObserver(new GameNotify(game,game.getPlayers()));
		}
	}

	@Override
	public String toString(Game game) {
		return "Waiting for players...";
	}

}
