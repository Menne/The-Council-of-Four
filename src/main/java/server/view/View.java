package server.view;

import observerPattern.Observable;
import observerPattern.Observer;
import server.model.actions.Action;
import server.view.serverSocketViewNotifies.ViewNotify;

public abstract class View extends Observable<Action> implements Observer<ViewNotify> {

	public View(){
	}
	
	@Override
	public abstract void update(ViewNotify notify);

}
