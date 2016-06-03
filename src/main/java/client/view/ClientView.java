package client.view;

import client.view.notifies.ClientViewNotify;
import modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class ClientView extends Observable<ClientNotify> implements Observer<ClientViewNotify> {

	@Override
	public abstract void update(ClientViewNotify notify);

}