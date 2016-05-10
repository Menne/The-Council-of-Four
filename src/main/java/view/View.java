package view;

import controller.GameLogic;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class View extends Observable implements Observer {

	public View(GameLogic gameLogic){
		gameLogic.registerObserver(this);
	}
	
	@Override
	public abstract <C> void update(Observable o, C change);

}
