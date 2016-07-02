package server.model.actions.marketActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;
import server.model.player.Player;
import server.view.notifies.MessageNotify;
import server.view.notifies.PlayerNotify;

/**
 * This class is the action of the market "make an offer"
 * @author cg31
 *
 */
public class MakeAnOffer implements Action{

	private List<Offer> offeringObjects;
	
	public void addOfferToList(Offer offer) {
		this.offeringObjects.add(offer);
	}

	public MakeAnOffer() {
		this.offeringObjects=new ArrayList<>();
	}
	
	@Override
	public boolean executeAction(Game game) {
		if (this.offeringObjects==null)
			throw new NullPointerException("Paramters not setted");
		
		for (Offer offer : this.offeringObjects)
			game.getMarket().addOffer(offer);
		
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
			
		this.notifyPlayers(game);
		game.setState(game.getState().sellActionTransition(game));
		game.getState().updateClients(game);
		
		return true;
		
	}

	@Override
	public void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageNotify("Offer registered succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageNotify(game.getCurrentPlayer().getName()
				+ " added: " + this.offeringObjects + " to the market", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new MakeAnOfferDTO();
	}

}