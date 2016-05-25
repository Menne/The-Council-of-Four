package server.model.actions.marketActions;

import modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Marketable;

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
