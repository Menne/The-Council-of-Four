package bonus;

import model.Game;

/**
 * ScoreBonus implements assignBonus method
 * @author Andrea
 *TODO: IMPORTARE CLASSE SCORE
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
	 * assignBonus assigns to player p the "advancement" score
	 * @param currentPlayer
	 */
	public void assignBonus(Game game){
		game.getCurrentPlayer().incrementScore(scoreAdvancement, game.getCurrentPlayer().getScore());
	}
}
