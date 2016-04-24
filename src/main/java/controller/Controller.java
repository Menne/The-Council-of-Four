package controller;

import observerPattern.*;
import model.*;

/**
 * It observes the View.
 * It transforms the View's notify into actions.
 * @author Luca
 *
 */
public class Controller implements Observer {

	Model game;
	
	public Controller(View view, Model game){
		this.game=game;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void play(GameState s) {

	}

}