package controller;

import model.Game;
import observerPattern.*;
import view.View;

/**
 * It observes the View.
 * It transforms the View's notify into actions.
 * @author Luca
 *
 */
public class Controller extends Observable implements Observer{

	private final Game game;
	
	public Controller(View view, Game game){
		this.game=game;
		view.registerObserver(this);
		this.notifyObservers(this.game);
	}
		
	public Game getGame() {
		return game;
	}

	@Override
	public <C> void update(Observable o, C change) {
		// TODO Auto-generated method stub
		
	}

}