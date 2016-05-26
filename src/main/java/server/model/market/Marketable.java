package server.model.market;

import players.Player;

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
