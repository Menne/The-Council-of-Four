package server.model.bonus;

import server.model.Game;

/**
 * This bonus allows the current player to choose from a city in which he has already built,
 * and then assigns him the bonus/bonuses associated to the city
 * This is a particular bonus which requires a choice by the current player.
 * In order to do this the method assignBonus invokes a particular action which
 * manages the assignment of the bonus
 * @author Emanuele
 *
 */

public class ChooseCityBonus implements Bonus{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4260484811602988062L;

	/**
	 * Invokes the action associated o this bonus
	 * @param game is the current game
	 */
	public void assignBonus(Game game) {
		//TODO
	}

}