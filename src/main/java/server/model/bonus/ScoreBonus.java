package server.model.bonus;

import players.Player;
import server.model.Game;

/**
 * ScoreBonus implements assignBonus method
 * @author Andrea
 */

public class ScoreBonus implements Bonus{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084023448270909822L;
	private final int scoreAdvancement;
	
	/**
	 * Constructor of ScoreBonus
	 * @param scoreAdvancement is the amount of advancement in the score track
	 * @throws IllegalArgumentException if scoreAdvancement is negative
	 */
	public ScoreBonus(int scoreAdvancement) throws IllegalArgumentException{
		if(scoreAdvancement<0)
			throw new IllegalArgumentException("you can't initialize a bonus with a negative advancement");
		this.scoreAdvancement=scoreAdvancement;
	}
	/**
	 * assignBonus assigns to player p the "advancement" score
	 * @param currentPlayer
	 */
	@Override
	public void assignBonus(Game game){
		game.getCurrentPlayer().incrementScore(scoreAdvancement);
	}
	
	public void assignBonusToPlayer(Player player){
		player.incrementScore(this.scoreAdvancement);
	}
	
	@Override
	public String toString() {
		return "Score+" + scoreAdvancement;
	}
}
