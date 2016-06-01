package server.view;

import java.util.List;

import observerPattern.Observable;
import observerPattern.Observer;
import players.Player;
import server.model.actions.Action;
import server.view.notifies.ViewNotify;

public abstract class View extends Observable<Action> implements Observer<ViewNotify> {

	public View(){
	}
	
	@Override
	public abstract void update(ViewNotify notify);
	

}
