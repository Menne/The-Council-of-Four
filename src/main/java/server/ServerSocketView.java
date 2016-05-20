package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Game;
import model.actions.Action;
import query.Query;
import view.View;
import view.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private final Game game;
	
	public ServerSocketView(Socket socket, Game game) throws IOException{
		this.socket=socket;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
		this.game=game;
	}
	
	
	@Override
	public void run() {
		while(true){
			
			try {
				
				Object object = this.socketIn.readObject();
				if (object instanceof Action){
					
					Action action=(Action) object;				
					this.notifyObserver(action);
				}
				if(object instanceof Query){
					Query query=(Query) object;
					this.socketOut.writeObject(query.perform(this.game));
					this.socketOut.flush(); 
				}
				
				
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
