package client.connections;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modelDTO.clientNotifies.ClientNotify;

@FunctionalInterface
public interface ClientRMIViewRemote extends Remote{

	public void updateClient(ClientNotify clientNotify) throws RemoteException;
}
