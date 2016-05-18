package model.actions.marketActions;

import model.Game;
import model.actions.Action;
import model.market.Offer;
import model.parser.ActionParserVisitor;

public class AcceptAnOffer implements Action {
	
	private final Game game;
	private Offer offer;
	
	public AcceptAnOffer(Game game){
		this.game=game;
	}


	@Override
	public ActionParserVisitor setParser() {
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
	public boolean executeAction(){
		this.game.getMarket().buyOffer(this.offer, this.game.getCurrentPlayer());
		this.game.setState(this.game.getState().buyActionTransition(game));
		return true;
	}

}
