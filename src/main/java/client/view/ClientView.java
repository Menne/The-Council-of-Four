package client.view;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

import client.view.notifies.ClientViewNotify;
import modelDTO.clientNotifies.ClientNotify;
import observerPattern.Observable;
import observerPattern.Observer;

public abstract class ClientView extends Observable<ClientNotify> implements Observer<ClientViewNotify>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6692372505563332254L;

	@Override
	public abstract void update(ClientViewNotify notify);
	
	public abstract void input() throws RemoteException, IOException;

}