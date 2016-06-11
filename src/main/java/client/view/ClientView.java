package client.view;

import java.io.Serializable;
import java.rmi.RemoteException;

import client.view.notifies.ClientViewNotify;
import observerPattern.Observer;

public abstract class ClientView implements Observer<ClientViewNotify>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6692372505563332254L;
	protected final Connection connection;
	
	public ClientView(Connection connection) {
		this.connection=connection;
	}

	@Override
	public abstract void update(ClientViewNotify notify);
	
	public abstract void input() throws RemoteException;
	
	public abstract void welcome(String name) throws RemoteException;

}