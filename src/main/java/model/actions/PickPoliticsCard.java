package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;
import model.parser.PickPoliticsCardParser;

public class PickPoliticsCard implements Action {

	private final Game game;
	
	public PickPoliticsCard(Game game) {
		this.game=game;
	}
	
	@Override
	public boolean executeAction() {
		this.game.getCurrentPlayer().getHand().add(this.game.getGameTable().getPoliticsDeck().pickCard());
		
		this.game.setState(this.game.getState().pickPoliticsCardTransition());
		return true;
	}

	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}
	
	
	@Override
	public ActionParserVisitor setParser() {
		return new PickPoliticsCardParser(this);	
	}

}
