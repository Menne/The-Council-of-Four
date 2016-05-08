package bonus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import model.Game;

/**
 * ScoreBonus implements assignBonus method
 * @author Andrea
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class ScoreBonus implements Bonus{
	
	@XmlElement(name="ScoreBonus")
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
	
	@Override
	public String toString() {
		return "Score+" + scoreAdvancement;
	}
}
