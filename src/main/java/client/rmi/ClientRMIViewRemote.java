package client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelDTO.clientNotifies.ClientNotify;

public interface ClientRMIViewRemote extends Remote{

	public void updateClient(ClientNotify clientNotify) throws RemoteException;
}
