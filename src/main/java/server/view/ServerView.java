package server.view;

import observerPattern.Observable;
import observerPattern.Observer;
import server.Server;
import server.model.actions.Action;
import server.view.notifies.ViewNotify;

/**
 * Abstract view of the Server MVC. The two implementation in our project are Socket and RMI
 * @author Luca Scannapieco
 *
 */
public abstract class ServerView extends Observable<Action> implements Observer<ViewNotify> {
	
	protected final Server server;

	/**
	 * @param server that is creating the view
	 */
	public ServerView(Server server){
		this.server=server;
	}
	
	/**
	 * The method the receives the notifies from the game.
	 */
	@Override
	public abstract void update(ViewNotify notify);
	

}
