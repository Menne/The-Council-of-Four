package view;

import model.Game;
import model.actions.Action;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class View extends Observable<Action> implements Observer<ViewNotify> {

	public View(){
	}
	
	@Override
	public abstract void update(ViewNotify notify);

}
