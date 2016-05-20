package model.actions.marketActions;

import model.Game;
import model.actions.Action;
import model.market.Offer;
import model.parser.ActionParserVisitor;

public class AcceptAnOffer implements Action {

	
	private Offer offer;

	@Override
	public ActionParserVisitor setParser(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

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
