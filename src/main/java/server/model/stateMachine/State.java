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
	
	public default State pickPoliticsCardTransition() throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State sellActionTransition(Game game) throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	
	public default State buyActionTransition(Game game) throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}


	public State moveToNextTransition(Game game);
	
	
	public default State additionalMainActionTransition() throws IllegalStateException{
		
		throw new IllegalStateException("There are not such transictions for this state");
	}

	public default State interactiveBonusTransition(){
		
		throw new IllegalStateException("There are not such transictions for this state");
	}
	
	
	public List<Action> getAcceptableActions(Game game);
	
	public void updateClients(Game game);


	

}
