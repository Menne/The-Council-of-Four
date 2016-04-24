package bonus;

import players.Player;

/**
 * NobilityBonus Class
 * @author andreapasquali
 *
 */

public class NobilityBonus implements Bonus{

	private final int advancement;
	
	public NobilityBonus(int advancement){
		this.advancement=advancement;
	}
	
	/**
	 * assigns to player p the "advancement" increase in nobility
	 * @param p
	 */
	public void assignBonus(Player p) {
		p.incrementNobility(advancement);
	}

}