package client.view;

import java.io.Serializable;
import java.rmi.RemoteException;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observable;

public abstract class Connection extends Observable<ClientNotify> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4867935731783639638L;

	public abstract void sendAction(ActionDTO action) throws RemoteException;
}
