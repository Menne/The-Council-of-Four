package server.model.bonus;

import server.model.Game;

/**
 * NobilityBonus Class
 * @author Andrea, Emanuele
 *
 */

public class NobilityBonus implements Bonus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774296693320586611L;
	private final int nobilityAdvancement;
	
	/**
	 * Constructor of NobilityBonus
	 * @param nobilityAdvancement is the amount of advancement in the nobility track
	 * @throws IllegalArgumentException if nobility advancement is negative or 0
	 */
	public NobilityBonus(int nobilityAdvancement) throws IllegalArgumentException{
		if(nobilityAdvancement<=0)
			throw new IllegalArgumentException("a nobility advancement must be positive and not 0");
		this.nobilityAdvancement=nobilityAdvancement;
	}
	
	/**
	 * Assigns to the currentPlayer an advancement in the nobility track.
	 * If the new nobility slot has bonuses inside it will assign them to the player
	 * @param currentPlayer is the player who is playing the turn
	 */
	@Override
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementNobility(nobilityAdvancement, game.getGameTable().getNobilityTrack().getTrack().size());
		int currentNobility=game.getCurrentPlayer().getNobility();
		if(!game.getGameTable().getNobilityTrack().getTrack().get(currentNobility).isEmpty())
			for(Bonus bonus : game.getGameTable().getNobilityTrack().getTrack().get(currentNobility))
				bonus.assignBonus(game);			
	}

	@Override
	public String toString() {
		return "Nobility+" + nobilityAdvancement;
	}

}