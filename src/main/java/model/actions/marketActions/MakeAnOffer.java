package model.actions.marketActions;

import model.Game;
import model.actions.Action;
import model.market.Marketable;
import model.parser.ActionParserVisitor;

public class MakeAnOffer implements Action{
	
	private final Game game;
	private Marketable offeringObject;
	private int price;
	
	public MakeAnOffer(Game game){
		this.game=game;
	}
	
	
	public void setOfferingObject(Marketable offeringObject) {
		this.offeringObject = offeringObject;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public boolean executeAction() {
		this.game.getMarket().addOffer(
				this.game.getMarket().getCurrentPlayer(), offeringObject, price);
		return true;
	}

	@Override
	public ActionParserVisitor setParser() {
		// TODO Auto-generated method stub
		return null;
	}

}
