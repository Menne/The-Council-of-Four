package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;

public class MoveToNext extends QuickAction {

	public MoveToNext(Game game){
		super(game);
	}
	
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction() {
		if(this.game.getCurrentPlayer().getPlayerNumber()!=this.game.getPlayers().size())
			this.game.nextPlayer();
		else
			this.game.startMarket();
		 
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
