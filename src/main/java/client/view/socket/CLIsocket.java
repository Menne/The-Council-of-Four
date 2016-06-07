package client.view.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import client.view.ClientView;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.actionsDTO.QuitDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.parser.Parser;


public class CLIsocket extends ClientView implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8153931800059106395L;
	private final Parser parser;
	private transient final Scanner scanner;
	private transient final Socket socket;
	private transient final ObjectOutputStream socketOut;
	private transient final ObjectInputStream socketIn;
	
	public CLIsocket(Parser parser, Socket socket) throws IOException {
		
		this.parser=parser;
		this.socket=socket;
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.scanner=new Scanner(System.in);
	}
	
	public Scanner getScanner() {
		return this.scanner;
	}
	
	

	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(scanner);
	}
	
	@Override
	public void input() throws IOException {
		String input="";
		while (!"quit".equals(input)) {

			input=this.scanner.nextLine();
			if (this.parser.availableActions().contains(input)) {
				ActionDTO selectedAction=this.parser.actionParser(input);
				this.checkIfParametersNeeded(selectedAction);
			}
			else if("quit".equals(input)){
				socketOut.writeObject(new QuitDTO(this));
				socketOut.flush();
			}
				else
					System.out.println("Sorry, action not available!");	
		}
	}
	
	private void checkIfParametersNeeded(ActionDTO selectedAction) {
		if (selectedAction instanceof ActionWithParameters) {
			ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
			this.insertParameters(actionWithParameters);
		}
		else{
			try {
				socketOut.writeObject(selectedAction);
				socketOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void insertParameters(ActionWithParameters selectedAction) {
		this.parser.parametersParser(selectedAction);
		if (selectedAction.checkIfParametersSetted()){
			try {
				
				socketOut.writeObject(selectedAction);
				socketOut.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void welcome(){
		AddPlayerDTO actionDTO=new AddPlayerDTO();
		System.out.println("Welcome to a new game of CoF! Please, tell me your name:");
		String input=scanner.nextLine();
		actionDTO.setPlayerName(input);
		try {
			
			socketOut.writeObject(actionDTO);
			socketOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true){
			try {
				Object object = socketIn.readObject();
				ClientNotify clientNotify=(ClientNotify) object;
				this.notifyObserver(clientNotify);
				
			} catch (ClassNotFoundException | IOException e) {
				try {
					socket.close();
					break;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}
	}

}
