package controller;

import model.Game;
import observerPattern.Observable;
import observerPattern.Observer;
import view.View;

public abstract class Turn extends Observable implements Observer{
	
	protected final Game game;
	
	public Turn(View view, Game game) {
		this.game=game;
		view.registerObserver(this);
	}
	
	public abstract void executeTurn();

	@Override
	public abstract <C> void update(Observable o, C change);

}