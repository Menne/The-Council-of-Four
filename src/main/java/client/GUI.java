package client;

import java.rmi.RemoteException;

import client.view.ClientView;
import client.view.Connection;
import client.view.notifies.ClientViewNotify;
import javafx.application.Application;


public class GUI extends ClientView {

	private final MainApp mainApp;
	
	public GUI(Connection connection) {
		super(connection);
		this.mainApp=new MainApp();
		Application.launch(MainApp.class);
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

	@Override
	public Object askForInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void output(Object object) {
		// TODO Auto-generated method stub
		
	}


}
