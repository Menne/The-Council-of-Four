package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import client.ModelDTO.GameDTO;



public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private final GameDTO clientGame;
	
	

	public ClientOutHandler(ObjectOutputStream socketOut, GameDTO game) {
		this.socketOut = socketOut;
		this.clientGame=game;
	}
	
	
	@Override
	public void run() {
		Scanner stdin=new Scanner(System.in);
		
		while(true){
			
			try {
				
				String inputLine = stdin.nextLine();
				socketOut.writeObject(inputLine);
				socketOut.flush();

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}

	}

}
