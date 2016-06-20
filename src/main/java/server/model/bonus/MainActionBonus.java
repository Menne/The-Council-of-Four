package server.model.bonus;

import server.model.Game;

/**
 * Allows the current player to take another main action
 * @author Emanuele
 *
 */

public class MainActionBonus implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913736619122310756L;
	private final int id;
	
	public MainActionBonus() {
		this.id=0;
	}

	/**
	 * Increments the number of available main actions available
	 * @param game is the current game
	 */
	@Override
	public void assignBonus(Game game) {
		game.setState(game.getState().additionalMainActionTransition());
		game.getState().updateClients(game);
	}

	@Override
	public String toString() {
		return "mainAction+1";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainActionBonus other = (MainActionBonus) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}