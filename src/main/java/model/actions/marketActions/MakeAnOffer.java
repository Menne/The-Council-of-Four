package model.actions.marketActions;

import client.actionDTO.ActionDTO;
import model.Game;
import model.actions.Action;
import model.market.Marketable;

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
	public ActionDTO map() {
		// TODO Auto-generated method stub
		return null;
	}


}
