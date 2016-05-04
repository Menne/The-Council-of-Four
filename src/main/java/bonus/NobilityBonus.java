package bonus;

import model.Game;

/**
 * NobilityBonus Class
 * @author Andrea, Emanuele
 *
 */

public class NobilityBonus implements Bonus{

	private final int nobilityAdvancement;
	
	/**
	 * Constructor of NobilityBonus
	 * @param nobilityAdvancement is the amount of advancement in the nobility track
	 */
	public NobilityBonus(int nobilityAdvancement){
		this.nobilityAdvancement=nobilityAdvancement;
	}
	
	/**
	 * Assigns to the currentPlayer an advancement in the nobility track.
	 * If the new nobility slot has bonuses inside it will assign them to the player
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementNobility(nobilityAdvancement, game.getGameTable().getNobilityTrack().getTrack().size());
		int currentNobility=game.getCurrentPlayer().getNobility();
		if(!game.getGameTable().getNobilityTrack().getTrack().get(currentNobility).isEmpty())
			for(Bonus bonus : game.getGameTable().getNobilityTrack().getTrack().get(currentNobility))
				bonus.assignBonus(game);			
	}

}