package client.view;

import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class ClientView extends Observable<ActionDTO> implements Observer<ClientViewNotify> {

	@Override
	public abstract void update(ClientViewNotify notify);

}