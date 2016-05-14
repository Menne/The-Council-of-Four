package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;

public class MoveToNext extends QuickAction {

	public MoveToNext(Game game){
		super(game);
	}

	@Override
	public boolean executeAction() {
		this.game.normalNextPlayer();
		
		this.game.setState(this.game.getState().moveToNextTransition());
		return true;
	}

	@Override
	public String toString() {
		return "exit: if you want to finish the turn";
	}

	@Override
	public ActionParserVisitor setParser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
