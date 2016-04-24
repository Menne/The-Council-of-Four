package bonus;

import players.Player;

/**
 * ScoreBonus implements assignBonus method
 * @author andreapasquali
 *TODO: IMPORTARE CLASSE SCORE
 */

public class ScoreBonus implements Bonus{
	
	private final int advancement;
	
	public ScoreBonus(int advancement){
		this.advancement=advancement;
	}
	/**
	 * assignBonus assigns to player p the "advancement" score
	 * @param p
	 */
	public void assignBonus(Player p){
		p.incrementScore(advancement, p.getScore());
	}
}
