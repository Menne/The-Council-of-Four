package client.view;

import client.controller.ControllerNotify;
import client.view.notifies.ClientViewNotify;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class ClientView extends Observable<ControllerNotify> implements Observer<ClientViewNotify> {

	@Override
	public abstract void update(ClientViewNotify notify);

}