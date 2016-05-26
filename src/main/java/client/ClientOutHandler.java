package client;

import java.io.ObjectOutputStream;


public class ClientOutHandler implements Runnable {

	private final ObjectOutputStream socketOut;
	
	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}
	
	
	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}

	@Override
	public void run() {
		while (true);	
	}

}
