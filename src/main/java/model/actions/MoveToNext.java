package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;
import view.GameNotify;

public class MoveToNext extends QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		 
		game.setState(game.getState().moveToNextTransition(game));
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	@Override
	public String toString() {
		return "ex: if you want to finish the turn";
	}

	@Override
	public ActionParserVisitor setParser(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
