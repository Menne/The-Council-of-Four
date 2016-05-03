package bonus;

import model.Game;

/**
 * NobilityBonus Class
 * @author Andrea, Emanuele
 *
 */

public class NobilityBonus implements Bonus{

	private final int nobilityAdvancement;
	
	/**
	 * Constructor of NobilityBonus
	 * @param nobilityAdvancement is the amount of advancement in the nobility track
	 */
	public NobilityBonus(int nobilityAdvancement){
		this.nobilityAdvancement=nobilityAdvancement;
	}
	
	/**
	 * Assigns to the currentPlayer an advancement in the nobility track
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementNobility(nobilityAdvancement);
	}

}