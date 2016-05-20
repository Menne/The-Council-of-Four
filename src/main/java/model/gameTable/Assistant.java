package model.gameTable;

import model.market.Marketable;
import players.Player;

public class Assistant implements Marketable {



	@Override
	public void addObjectToPlayer(Player player) {
		
		player.incrementAssistants(1);
	}
	
	@Override
	public void removeObjectFromPlayer(Player player) {
		player.decrementAssistants(1);
	}

}
