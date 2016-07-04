package client.connections;

import java.rmi.RemoteException;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.modelDTO.actionsDTO.QuitDTORMI;
import clientUpdates.ClientUpdate;
import server.view.RMIViewRemote;

/**
 * The class that handle the RMI connection on the client side.
 * @author cg31
 *
 */
public class RMIConnection extends Connection implements ClientRMIViewRemote{
	

	private final RMIViewRemote serverStub;
	
	public RMIConnection(RMIViewRemote serverStub) {
		this.serverStub=serverStub;
	}

	/**
	 * Remote method called from the server the send notifies to the clients
	 */
	@Override
	public void updateClient(ClientUpdate clientNotify) throws RemoteException {
		this.notifyObserver(clientNotify);		
	}
	
	/**
	 * Sends the action to the server using the server stub.
	 */
	@Override
	public void sendAction(ActionDTO action) throws RemoteException{
		if(!(action instanceof AddPlayerDTO)&&!(action instanceof ChooseMapDTO))
			this.getTimerTask().cancel();
		if(action instanceof QuitDTO)
			this.serverStub.receiveAction(new QuitDTORMI(this));
		else if(action instanceof AddPlayerDTO)
			this.serverStub.registerClient(this, ((AddPlayerDTO)action).getPlayerName());
		else
			this.serverStub.receiveAction(action);
	}
	
}
