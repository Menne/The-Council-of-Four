package server.view;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import client.rmi.ClientRMIViewRemote;
import modelDTO.actionsDTO.ActionDTO;
import server.Server;
import server.view.notifies.ViewNotify;

public class RMIView extends View implements RMIViewRemote {
	
	private final Set<ClientRMIViewRemote> clients;
	
	
	public RMIView(Server server){
		super(server);
		this.clients=new HashSet<>();
	}
	
	
	@Override
	public void update(ViewNotify notify) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerClient(ClientRMIViewRemote clientStub) throws RemoteException {
		this.clients.add(clientStub);
		System.out.println("client registred");
		
	}

	@Override
	public void receiveAction(ActionDTO actionDTO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


}
