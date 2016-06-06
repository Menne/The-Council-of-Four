package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) throws NotBoundException, UnknownHostException, IOException {
		String input=null;
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to CoF, do you want to use Socket or RMI?");
		while(!"Socket".equals(input) && !"RMI".equals(input)){
			input=scanner.nextLine();
			if(!"Socket".equals(input) && !"RMI".equals(input))
				System.out.println("Wrong input. Try again.");
		}
		if("RMI".equals(input)){
			ClientRMI clientRMI=new ClientRMI();
			clientRMI.startClient();
		}
		else{
			ClientSocket clientSocket=new ClientSocket();
			clientSocket.startClient();
		}
		scanner.close();
	}

}
