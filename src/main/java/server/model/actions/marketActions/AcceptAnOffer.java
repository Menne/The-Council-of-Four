package server.model.actions.marketActions;

import java.util.Arrays;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;
import server.view.notifies.MessageNotify;

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
		
		if (!checkCoins(game)) {
			game.notifyObserver(new MessageNotify("It seems that you haven't enough coins!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementCoins(offer.getPrice());
		offer.getOfferingPlayer().incrementCoins(offer.getPrice());
		offer.getOfferedObject().addObjectToPlayer(game.getCurrentPlayer());
		offer.getOfferedObject().removeObjectFromPlayer(offer.getOfferingPlayer());
		
		game.getMarket().removeOffer(this.offer);
		
		game.setState(game.getState().buyActionTransition(game));
		game.getState().updateClients(game);
		
		return true;
	}

	private boolean checkCoins(Game game) {
		if (this.offer.getPrice() >= game.getCurrentPlayer().getCoins())
			return false;
		return true;
	}

	@Override
	public ActionDTO map() {
		return new AcceptAnOfferDTO();
	}

}
