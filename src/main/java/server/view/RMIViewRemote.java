package server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.view.rmi.ClientRMIViewRemote;
import modelDTO.actionsDTO.ActionDTO;


public interface RMIViewRemote extends Remote {

	public void registerClient(ClientRMIViewRemote clientStub, String playerName) throws RemoteException;
	
	public void receiveAction(ActionDTO actionDTO) throws RemoteException;
	
	public void quitPlayer(ClientRMIViewRemote quittingView) throws RemoteException;
}
