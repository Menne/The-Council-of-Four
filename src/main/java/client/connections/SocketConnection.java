package client.connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
			if(!(action instanceof AddPlayerDTO)&&!(action instanceof ChooseMapDTO)&&getTimerTask()!=null)
				this.getTimerTask().cancel();
			socketOut.writeObject(action);
			socketOut.flush();
			socketOut.reset();
		} catch (IOException e) {
			Logger logger=Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "Failed to send action with socket", e);
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
					
					Logger logger=Logger.getAnonymousLogger();
					logger.log(Level.INFO, "Socket closed!", e);
					socket.close();
					return;
					
				} catch (IOException e1) {
					Logger logger=Logger.getAnonymousLogger();
					logger.log(Level.SEVERE, "Failed to close the socket", e1);
				}
			}	
			ClientNotify clientNotify=(ClientNotify) object;
			this.notifyObserver(clientNotify);
		}
	}

}
