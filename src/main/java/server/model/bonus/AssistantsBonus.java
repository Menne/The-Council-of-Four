package server.model.bonus;

import server.model.Game;

/**
 * AssistantsBonus implements the Bonus Class
 * @author Andrea, Emanuele
 *
 */

public class AssistantsBonus implements Bonus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2791888304194029622L;
	private final int assistantsIcreasement;

	/**
	 * Constructor of AssistantsBonus
	 * @param assistantsIcreasement is the increasement of assistants
	 * @throws IllegalArgumentException if assistantIncreasement is 0 or negative
	 */
	public  AssistantsBonus(int assistantsIcreasement) throws IllegalArgumentException{
		if(assistantsIcreasement<=0)
			throw new IllegalArgumentException("you can't create a bonus with 0 or negative number of assistants");
		this.assistantsIcreasement=assistantsIcreasement;
	}
	
	/**
	 * Assigns to the current player a number assistantsIcreasement of assistants
	 * @param currentPlayer is the player who is playing the turn
	 */
	@Override
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementAssistants(assistantsIcreasement);
	}

	@Override
	public String toString() {
		return "assistants+" + assistantsIcreasement;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assistantsIcreasement;
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
		AssistantsBonus other = (AssistantsBonus) obj;
		if (assistantsIcreasement != other.assistantsIcreasement)
			return false;
		return true;
	}

}