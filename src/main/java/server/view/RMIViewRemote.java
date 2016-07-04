package server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.connections.ClientRMIViewRemote;
import client.modelDTO.actionsDTO.ActionDTO;

/**
 * The server stub to export on the server registry
 * @author cg31
 *
 */
public interface RMIViewRemote extends Remote {
	/**
	 * Method called to register a new player 
	 * @param clientStub The client stub used from the view to send notifies to the client.
	 * @param playerName The name of the new player.
	 * @throws RemoteException because remote method  
	 */
	public void registerClient(ClientRMIViewRemote clientStub, String playerName) throws RemoteException;
	
	
	/**
	 * Method called to send actions to the server
	 * @param actionDTO The action to send.
	 * @throws RemoteException because Remote method
	 */
	public void receiveAction(ActionDTO actionDTO) throws RemoteException;
}
