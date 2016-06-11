package client;

import java.rmi.RemoteException;

import client.view.ClientView;
import client.view.Connection;
import client.view.notifies.ClientViewNotify;


public class GUI extends ClientView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4145493495640741715L;
	
	public GUI(Connection connection) {
		super(connection);
	}

	@Override
	public void update(ClientViewNotify notify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void welcome(String name) {
		// TODO Auto-generated method stub
		
	}


}
