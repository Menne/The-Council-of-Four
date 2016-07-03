package server.model.actions.marketActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;
import server.model.player.Player;
import server.serverNotifies.MarketServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * This class is the action of the market "accept an offer", it operates on the 
 * protected attribute game through the method executeAction.
 * @author cg31
 *
 */
public class AcceptAnOffer implements Action {

	private Offer offer;

	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	@Override
	public boolean executeAction(Game game) {
		if (this.offer==null)
			throw new NullPointerException("Paramters not setted");
		
		if (!checkCoins(game)) {
			game.notifyObserver(new MessageServerNotify("It seems that you haven't enough coins!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementCoins(offer.getPrice());
		offer.getOfferingPlayer().incrementCoins(offer.getPrice());
		offer.getOfferedObject().addObjectToPlayer(game.getCurrentPlayer());
		offer.getOfferedObject().removeObjectFromPlayer(offer.getOfferingPlayer());
		
		game.getMarket().removeOffer(this.offer);
		
		this.updateClients(game);
		game.setState(game.getState().buyActionTransition(game));
		game.getState().updateAvailableActions(game);
		
		return true;
	}

	private boolean checkCoins(Game game) {
		if (this.offer.getPrice() >= game.getCurrentPlayer().getCoins())
			return false;
		return true;
	}
	
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new MarketServerNotify(game, 
				new ArrayList<Player>(game.getAllPlayers()), false, false));
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("Offer accepted succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " bought " + this.offer + " from the market", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new AcceptAnOfferDTO();
	}

}
