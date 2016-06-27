package server.model.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import server.model.Game;
import server.model.player.Player;
import server.view.notifies.MessageNotify;

public class PickPoliticsCard implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		this.notifyPlayers(game);
		game.setState(game.getState().pickPoliticsCardTransition());
		game.getState().updateClients(game);
		
		return true;
	}
	
	
	private void notifyPlayers(Game game) {
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
