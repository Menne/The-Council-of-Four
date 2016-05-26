package model.bonus;

import model.Game;
import players.Player;

/**
 * ScoreBonus implements assignBonus method
 * @author Andrea
 */

public class ScoreBonus implements Bonus{
	
	private final int scoreAdvancement;
	
	/**
	 * Constructor of ScoreBonus
	 * @param scoreAdvancement is the amount of advancement in the score track
	 */
	public ScoreBonus(int scoreAdvancement){
		this.scoreAdvancement=scoreAdvancement;
	}
	/**
	 * assignBonus assigns to the current player the "advancement" score
	 * @param game that has the current player and can assign directly to the right player this bonus
	 */
	public void assignBonus(Game game){
		game.getCurrentPlayer().incrementScore(scoreAdvancement);
	}
	
	/**
	 * assigns to player p the score bonus
	 * @param player to which the method assigns the bonus
	 */
	public void assignBonusToPlayer(Player player){
		player.incrementScore(this.scoreAdvancement);
	}
	
	@Override
	public String toString() {
		return "Score+" + scoreAdvancement;
	}
}
