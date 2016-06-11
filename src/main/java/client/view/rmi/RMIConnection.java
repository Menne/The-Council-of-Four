package client.view.rmi;

import java.rmi.RemoteException;

import client.view.Connection;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.clientNotifies.ClientNotify;
import server.view.RMIViewRemote;

public class RMIConnection extends Connection implements ClientRMIViewRemote{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6062596454352046673L;
	private final RMIViewRemote serverStub;
	
	public RMIConnection(RMIViewRemote serverStub) {
		this.serverStub=serverStub;
	}

	
	@Override
	public void updateClient(ClientNotify clientNotify) throws RemoteException {
		this.notifyObserver(clientNotify);		
	}
	
	@Override
	public void sendAction(ActionDTO action) throws RemoteException{
		if(action instanceof AddPlayerDTO)
			this.serverStub.registerClient(this, ((AddPlayerDTO)action).getPlayerName());
		else
			this.serverStub.receiveAction(action);
	}
	
}
