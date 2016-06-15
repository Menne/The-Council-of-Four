package server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modelDTO.actionsDTO.ActionDTO;
import client.view.rmi.ClientRMIViewRemote;


public interface RMIViewRemote extends Remote {

	public void registerClient(ClientRMIViewRemote clientStub, String playerName) throws RemoteException;
	
	public void receiveAction(ActionDTO actionDTO) throws RemoteException;
}
