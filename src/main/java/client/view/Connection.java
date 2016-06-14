package client.view;


import java.rmi.RemoteException;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observable;

public abstract class Connection extends Observable<ClientNotify> {


	public abstract void sendAction(ActionDTO action) throws RemoteException;
}
