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
import server.serverNotifies.MarketServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * This class is the action of the market "make an offer"
 * @author cg31
 *
 */
public class MakeAnOffer implements Action{

	private List<Offer> offeringObjects;
	
	public MakeAnOffer() {
		this.offeringObjects=new ArrayList<>();
	}
	
	public void addOfferToList(Offer offer) {
		this.offeringObjects.add(offer);
	}
	
	public List<Offer> getOfferingObjects() {
		return offeringObjects;
	}

	@Override
	public boolean executeAction(Game game) {
		if (this.offeringObjects==null)
			throw new NullPointerException("Paramters not setted");
		
		for (Offer offer : this.offeringObjects)
			game.getMarket().addOffer(offer);
		
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
			
		this.updateClients(game);
		game.setState(game.getState().sellActionTransition(game));
		game.getState().updateAvailableActions(game);
		
		return true;
		
	}

	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new MarketServerNotify(game, 
				new ArrayList<Player>(game.getAllPlayers()), false, false));
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("Offer registered succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " added: " + this.offeringObjects + " to the market", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new MakeAnOfferDTO();
	}

}