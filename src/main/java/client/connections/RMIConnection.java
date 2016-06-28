package client.connections;

import java.rmi.RemoteException;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.modelDTO.actionsDTO.QuitDTORMI;
import client.modelDTO.clientNotifies.ClientNotify;
import server.view.RMIViewRemote;

public class RMIConnection extends Connection implements ClientRMIViewRemote{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8046940151913208221L;
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
