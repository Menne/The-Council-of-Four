package bonus;

import players.Player;

/**
 * CoinsBonus class
 * @author andreapasquali
 *
 */

public class CoinsBonus implements Bonus{

	private final int increasementCoins;

	public CoinsBonus(int increasementCoins){
		this.increasementCoins=increasementCoins;
	}
	/**
	 * Assigns to player p an "increasement coins" number of coins
	 * @param p
	 */
	public void assignBonus(Player p) {
		p.incrementCoins(increasementCoins);
	}

}