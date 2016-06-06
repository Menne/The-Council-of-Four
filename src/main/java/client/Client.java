package client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.ConnectIOException;
import java.rmi.NotBoundException;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) throws NotBoundException, UnknownHostException, IOException {
		String input="";
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to CoF, do you want to use Socket or RMI?");
		while(!"Socket".equals(input) && !"RMI".equals(input)){
			input=scanner.nextLine();
			if(!"Socket".equals(input) && !"RMI".equals(input))
				System.out.println("Wrong input. Try again.");
		}
		System.out.println("Please insert the server address");
		while(true){
			String ip=scanner.nextLine();
			try{
				if("RMI".equals(input)){
					ClientRMI clientRMI=new ClientRMI(ip);
					clientRMI.startClient();
					break;
				}
				else{
					ClientSocket clientSocket=new ClientSocket(ip);
					clientSocket.startClient();
					break;
				}
			}catch(SocketException | ConnectIOException e){
				System.out.println("Server Unreachable. Try again");
			}
		}
		scanner.close();
	}

}
