package view;

import controller.Turn;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class View extends Observable implements Observer {

	public View(Turn turn){
		turn.registerObserver(this);
	}
	
	@Override
	public abstract <C> void update(Observable o, C change);

}
