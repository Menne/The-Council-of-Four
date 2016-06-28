package client.connections;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modelDTO.clientNotifies.ClientNotify;

@FunctionalInterface
public interface ClientRMIViewRemote extends Remote, Serializable{

	public void updateClient(ClientNotify clientNotify) throws RemoteException;
}
