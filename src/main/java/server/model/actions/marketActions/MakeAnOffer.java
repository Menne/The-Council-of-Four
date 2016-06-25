package server.model.actions.marketActions;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;

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
		if ((this.offeringObjects.size()==0))
			throw new NullPointerException("Paramters not setted");
		
		System.out.println("sono l'azione make an offer arrivata al server");
		
		for (Offer offer : this.offeringObjects)
			game.getMarket().addOffer(offer);
			
		game.setState(game.getState().sellActionTransition(game));
		game.getState().updateClients(game);
		
		return true;
		
	}


	@Override
	public ActionDTO map() {
		return new MakeAnOfferDTO();
	}

}