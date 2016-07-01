package server.model.actions;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.MoveToNextDTO;
import server.model.Game;
import server.model.player.Player;
import server.view.notifies.MessageNotify;

public class MoveToNext implements QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		
		this.notifyPlayers(game);
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateClients(game);
		
		return true;
	}

	@Override
	public void notifyPlayers(Game game) {
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageNotify(game.getCurrentPlayer().getName()
				+ " decided to skip the turn", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new MoveToNextDTO();
	}

}
