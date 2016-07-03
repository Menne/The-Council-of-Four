package client.connections;

import java.rmi.Remote;
import java.rmi.RemoteException;

import clientUpdates.ClientUpdate;

@FunctionalInterface
public interface ClientRMIViewRemote extends Remote{

	public void updateClient(ClientUpdate clientNotify) throws RemoteException;
}
