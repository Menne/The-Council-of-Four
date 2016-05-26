package model.bonus;

import players.Player;
import server.model.bonus.Bonus;

/**
 * AssistantsBonus implements the Bonus Class
 * @author Andrea, Emanuele
 *
 */

@SuppressWarnings("serial")
public class AssistantsBonus implements Bonus{

	private final int assistantsIcreasement;

	/**
	 * Constructor of AssistantsBonus
	 * @param assistantsIcreasement is the increasement of assistants
	 */
	public  AssistantsBonus(int assistantsIcreasement){
		this.assistantsIcreasement=assistantsIcreasement;
	}
	

	
	/**
	 * Assigns to player the number of assistances 
	 * @param player
	 */
	public void assignBonusToPlayer(Player player) {
		player.incrementAssistants(this.assistantsIcreasement);
	}

	@Override
	public String toString() {
		return "assistants+" + assistantsIcreasement;
	}

	/**
	 * Assigns to the current player a number assistantsIcreasement of assistants
	 * @param currentPlayer is the player who is playing the turn
	 */
	@Override
	public void assignBonus(server.model.Game game) {
		game.getCurrentPlayer().incrementAssistants(this.assistantsIcreasement);
		
	}

}