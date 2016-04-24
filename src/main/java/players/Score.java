package players;

	/** Player's score
	 * 
	 * @author Emanuele
	 *
	 */

public class Score {
	
	private int score;
	
	public Score(int score) {
		this.score=score;
	}
	
	public void incrementScore(int increment) {
		this.score=this.score+increment;
	}
	
}