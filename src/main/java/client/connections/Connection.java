package client.connections;


import java.rmi.RemoteException;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observable;

public abstract class Connection extends Observable<ClientNotify> {


	public abstract void sendAction(ActionDTO action) throws RemoteException;
}
