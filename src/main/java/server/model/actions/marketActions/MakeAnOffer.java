package server.model.actions.marketActions;

import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Marketable;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.PlayerNotify;

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
	public boolean executeAction(Game game) throws NullPointerException {
		if (this.offeringObject==null) throw new NullPointerException("Paramters not setted");
		
		game.getMarket().addOffer(
				game.getMarket().getCurrentPlayer(), offeringObject, price);
		game.setState(game.getState().sellActionTransition());
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		return true;
		
	}


	@Override
	public ActionDTO map() {
		return new MakeAnOfferDTO();
	}

}
