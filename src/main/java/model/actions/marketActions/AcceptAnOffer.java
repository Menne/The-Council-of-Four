package model.actions.marketActions;

import model.Game;
import model.actions.Action;
import model.market.Offer;

public class AcceptAnOffer implements Action {

	
	private Offer offer;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	@Override
	public boolean executeAction(Game game){
		game.getMarket().buyOffer(this.offer, game.getCurrentPlayer());
		game.setState(game.getState().buyActionTransition(game));
		return true;
	}

}
