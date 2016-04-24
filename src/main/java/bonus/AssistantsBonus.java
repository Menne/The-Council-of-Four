package bonus;

import players.Player;

/**
 * AssistantsBonus implements the Bonus Class
 * @author andreapasquali
 *
 */

public class AssistantsBonus implements Bonus{

	private final int increasementAssistants;

	public  AssistantsBonus(int increasementAssistants){
		this.increasementAssistants=increasementAssistants;
	}
	/**
	 * assigns to player p a number "increasementAssistant" of assistants
	 * @param p
	 */
	public void assignBonus(Player p) {
		p.incrementAssistants(increasementAssistants);
	}

}