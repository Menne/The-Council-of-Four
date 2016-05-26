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
	 */
	public ScoreBonus(int scoreAdvancement){
		this.scoreAdvancement=scoreAdvancement;
	}
	/**
	 * assignBonus assigns to player p the "advancement" score
	 * @param currentPlayer
	 */
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
