package server.model.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import server.model.Game;
import server.model.player.Player;
import server.view.notifies.MessageNotify;
import server.view.notifies.PlayerNotify;

/**
 * This class modelizes the action of picking a politics card from the politics deck, executed 
 * every time a player begins a new turn
 * @author cg31
 *
 */
public class PickPoliticsCard implements Action {

	/**
	 * Adds the picked politics card to the hand of the player
	 */
	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		this.notifyPlayers(game);
		game.setState(game.getState().pickPoliticsCardTransition());
		game.getState().updateClients(game);
		
		return true;
	}
	
	@Override
	public void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageNotify("Card picked!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageNotify(game.getCurrentPlayer().getName()
				+ " picked a politics card", otherPlayers));
	}

	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}


	@Override
	public ActionDTO map() {
		return new PickPoliticsCardDTO();
	}

}
