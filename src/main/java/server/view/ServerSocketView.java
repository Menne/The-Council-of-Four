  package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import modelDTO.actionsDTO.ActionDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.PlayerAddedNotify;
import server.view.notifies.ViewNotify;

public class ServerSocketView extends View implements Runnable {

	private final Socket socket;
	private final ObjectInputStream socketIn;
	private final ObjectOutputStream socketOut;
	private final Game game;
	private Player player;
	
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
			if(notify instanceof PlayerAddedNotify && this.player==null){
				PlayerAddedNotify playerAddedNotify=(PlayerAddedNotify) notify;
				this.player=playerAddedNotify.getPlayer();
				System.out.println("view di "+this.player.getName()+": settato player");
			}
			
			if((notify.sendTo().contains(this.player))){
				this.socketOut.writeObject(notify.toClientNotify());
				System.out.println("view: notifica inviata al player "+this.player.getName());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
