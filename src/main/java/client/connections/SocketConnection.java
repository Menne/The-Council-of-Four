package client.connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.modelDTO.clientNotifies.ClientNotify;

/**
 * Class that handle the socket connection on the client side
 * @author Luca Scannapieco
 *
 */
public class SocketConnection extends Connection implements Runnable{
	
	private final Socket socket;
	private final ObjectOutputStream socketOut;
	private final ObjectInputStream socketIn;
	
	public SocketConnection(Socket socket) throws IOException {
		
		this.socket=socket;
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
		this.socketIn=new ObjectInputStream(socket.getInputStream());
	} 

	/**
	 * @return the ObjectOutputStream that this class uses to send action to the server
	 */
	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}
	
	/**
	 * Sends action to the server using the ObjectOutputStream of the Socket.
	 */
	@Override
	public void sendAction(ActionDTO action){
		try {
			if(!(action instanceof AddPlayerDTO)&&!(action instanceof ChooseMapDTO))
				this.getTimerTask().cancel();
			socketOut.writeObject(action);
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method runs on a different thread and listens for notifies sent from the server 
	 */
	@Override
	public void run() {
		Object object=null;
		while (true){
			try {
				
				object = socketIn.readObject();
				
			} catch (ClassNotFoundException | IOException e) {
				try {
					
					socket.close();
					System.out.println("Connection closed!");
					return;
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			ClientNotify clientNotify=(ClientNotify) object;
			this.notifyObserver(clientNotify);
		}
	}

}
