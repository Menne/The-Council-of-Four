package server.model.stateMachine;

import java.util.List;

import server.model.Game;
import server.model.actions.Action;

public interface State{

	public default State mainActionTransition(Game game) throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State quickActionTransition(Game game)throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State additionalMainActionTransition() throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State pickPoliticsCardTransition() throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State sellActionTransition() throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State buyActionTransition(Game game) throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State moveToNextTransition(Game game) throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	public default void addPlayerTransition(Game game) throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

		
	public List<Action> getAcceptableActions(Game game);

	
}
