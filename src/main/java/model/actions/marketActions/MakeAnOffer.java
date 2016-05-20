package model.actions.marketActions;

import model.Game;
import model.actions.Action;
import model.market.Marketable;
import model.parser.ActionParserVisitor;

public class MakeAnOffer implements Action{
	

	private Marketable offeringObject;
	private int price;
	
	
	public void setOfferingObject(Marketable offeringObject) {
		this.offeringObject = offeringObject;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public boolean executeAction(Game game) {
		game.getMarket().addOffer(
				game.getMarket().getCurrentPlayer(), offeringObject, price);
		game.setState(game.getState().sellActionTransition());
		return true;
		
	}

	@Override
	public ActionParserVisitor setParser(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

}
