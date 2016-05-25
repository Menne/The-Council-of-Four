package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.actionDTO.ActionDTO;
import model.Game;
import model.actions.AddPlayer;
import view.View;
import view.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private final Game game;
	
	public ServerSocketView(Socket socket, Game game) throws IOException{
		this.socket=socket;
		this.game=game;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
	}
	
	
	@Override
	public void run() {
		while(true){
			
			try {

				Object object = this.socketIn.readObject();
					
				ActionDTO actionDTO=(ActionDTO) object;				
				this.notifyObserver(actionDTO.map(this.game));
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}


	@Override
	public void update(ViewNotify notify) {
		try {
			System.out.println("player almost Added");
			this.socketOut.writeObject(notify.toClientNotify());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
