package server.model.market;

import server.model.player.Player;

/**
 * is an interface that is implemented by every object that can be offered in the market
 * @author andreapasquali
 *
 */
public interface Marketable {

	/**
	 * adds the marketable object to the hand of player
	 * @param player is the player who will receive the object after having buying it
	 */
	void addObjectToPlayer(Player player);

	/**
	 * removes an object from a player who has sold it
	 * @param player who has sold the object and will loose it
	 */
	void removeObjectFromPlayer(Player player) throws IllegalArgumentException;
}
