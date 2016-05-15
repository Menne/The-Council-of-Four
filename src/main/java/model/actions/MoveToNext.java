package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;

public class MoveToNext extends QuickAction {

	public MoveToNext(Game game){
		super(game);
	}
	
	
	/**
	 * TODO
	 */
	@Override
	public boolean executeAction() {
		if(this.game.getCurrentPlayer().getPlayerNumber()==this.game.getPlayers().size())
			this.game.normalNextPlayer();
		if(this.game.isLastLap())
			this.game.lastLapNextPlayer();
		this.game.normalNextPlayer();
		 
		this.game.setState(this.game.getState().moveToNextTransition(this.game));
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
