package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Game;
import model.actions.Action;
import model.actions.AddPlayer;
import view.View;
import view.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	
	public ServerSocketView(Socket socket) throws IOException{
		this.socket=socket;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
		this.notifyObserver(new AddPlayer());
	}
	
	
	@Override
	public void run() {
		while(true){
			
			try {
				
				Object object = this.socketIn.readObject();
					
				Action action=(Action) object;				
				this.notifyObserver(action);
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}


	@Override
	public void update(ViewNotify notify) {
		// TODO Auto-generated method stub
		
	}

}
