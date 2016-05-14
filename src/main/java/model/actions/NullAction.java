package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;

public class NullAction extends QuickAction {

	public NullAction(Game game){
		super(game);
	}

	@Override
	public boolean executeAction() {
		
		this.game.setState(this.game.getState().nullActionTransition());
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
