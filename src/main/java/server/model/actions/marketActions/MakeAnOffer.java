package server.model.actions.marketActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.MarketNotify;
import server.view.notifies.PlayerNotify;

public class MakeAnOffer implements Action{

	private List<Offer> offeringObjects;
	
	public void addOfferToList(Offer offer) {
		this.offeringObjects.add(offer);
	}

	public MakeAnOffer() {
		this.offeringObjects=new ArrayList<>();
	}
	
	@Override
	public boolean executeAction(Game game) throws NullPointerException {
		if ((this.offeringObjects==null))
			throw new NullPointerException("Paramters not setted");
		
		for (Offer offer : this.offeringObjects)
			game.getMarket().addOffer(offer);
			
		game.setState(game.getState().sellActionTransition(game));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MarketNotify(game.getMarket(), 
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
