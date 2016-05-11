package view;

import model.Game;
import model.actions.Action;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class View extends Observable<Action> implements Observer<ViewNotify> {

	public View(Game Game){
		Game.registerObserver(this);
	}
	
	@Override
	public abstract void update(ViewNotify notify);

}
