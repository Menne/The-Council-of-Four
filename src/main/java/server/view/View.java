package server.view;

import observerPattern.Observable;
import observerPattern.Observer;
import server.Server;
import server.model.actions.Action;
import server.view.notifies.ViewNotify;

public abstract class View extends Observable<Action> implements Observer<ViewNotify> {
	
	protected final Server server;

	public View(Server server){
		this.server=server;
	}
	
	@Override
	public abstract void update(ViewNotify notify);
	

}
