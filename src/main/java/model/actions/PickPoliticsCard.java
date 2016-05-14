package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;

public class PickPoliticsCard implements Action {

	private final Game game;
	
	public PickPoliticsCard(Game game) {
		this.game=game;
	}
	
	@Override
	public boolean executeAction() {
		this.game.getCurrentPlayer().getHand().add(this.game.getGameTable().getPoliticsDeck().pickCard());
		
		this.game.setState(this.game.getState().quickActionTransition());
		return true;
	}

	@Override
	public ActionParserVisitor setParser() {
		// TODO Auto-generated method stub
		return null;
	}

}
