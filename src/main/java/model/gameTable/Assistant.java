package model.gameTable;

import model.market.Marketable;
import players.Player;

/**
 * modelize assistants and it contains only the methods used for the market (add and remove)
 * @author andreapasquali
 *
 */
public class Assistant implements Marketable {

	@Override
	public void addObjectToPlayer(Player player) {
		player.incrementAssistants(1);
	}
	
	@Override
	public void removeObjectFromPlayer(Player player) throws IllegalArgumentException{
		if(player.getNumberOfAssistants()<=0)
			throw new IllegalArgumentException("the selling player hasn't assistants to sell");
		player.decrementAssistants(1);
	}

}
