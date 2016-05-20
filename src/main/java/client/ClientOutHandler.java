package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import query.GetActions;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;

	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut = socketOut;
	}
	
	
	@Override
	public void run() {
		Scanner stdin=new Scanner(System.in);
		
		while(true){
			
			try {
				
				this.socketOut.writeObject(new GetActions());
				this.socketOut.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}

	}

}
