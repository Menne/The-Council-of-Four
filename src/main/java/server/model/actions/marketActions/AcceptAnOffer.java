package server.model.actions.marketActions;

import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.PlayerNotify;

public class AcceptAnOffer implements Action {

	private Offer offer;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	@Override
	public boolean executeAction(Game game) throws NullPointerException {
		if (this.offer==null)
			throw new NullPointerException("Paramters not setted");
		
		game.getMarket().buyOffer(this.offer, game.getCurrentPlayer());
		game.setState(game.getState().buyActionTransition(game));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		
		return true;
	}

	@Override
	public ActionDTO map() {
		return new AcceptAnOfferDTO();
	}

}
