package controller;

import java.util.List;

import model.Game;
import model.actions.Action;

public interface State{

	public default State mainActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	
	public default State quickActionTransition()throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	
	public default State additionalMainActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	
	public default State pickPoliticsCardTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	
	public default State sellActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	
	public default State buyActionTransition() throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

	
	public default State moveToNextTransition(Game game) throws RuntimeException{
		
		throw new RuntimeException("There are not such transictions for this state");
	}

		
	public List<Action> getAcceptableActions(Game game);

	
	public String toString(Game game);
	
}
