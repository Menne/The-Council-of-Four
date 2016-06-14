package client;

import java.rmi.RemoteException;
import java.util.Scanner;

import client.view.ClientView;
import client.view.Connection;
import client.view.notifies.ClientGameOverNotify;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.actionsDTO.QuitDTO;
import modelDTO.parser.Parser;

public class CLI extends ClientView {


	private final Parser parser;
	private final Scanner scanner;
	
	public CLI(Parser parser, Connection connection) {
		super(connection);
		this.parser=parser;
		this.scanner=new Scanner(System.in);
	}
	
	private boolean parametersNeeded(ActionDTO selectedAction) throws RemoteException {
		return (selectedAction instanceof ActionWithParameters);			
	}
	
	private void insertParametersAndSend(ActionWithParameters actionWithParameters) throws RemoteException {
		this.parser.parametersParser(actionWithParameters);
		if (actionWithParameters.checkIfParametersSetted())
			connection.sendAction(actionWithParameters);
		
	}
	
	@Override
	public void input() throws RemoteException {
		String input="";
		while (!"quit".equals(input)) {
			try{
				input=this.scanner.nextLine();
			}catch(IllegalStateException e){
				break;
			}
			if (this.parser.availableActions().contains(input)) {
				ActionDTO selectedAction=this.parser.actionParser(input);
				if (parametersNeeded(selectedAction)) {
					ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
					this.insertParametersAndSend(actionWithParameters);
				}
				else
					connection.sendAction(selectedAction);
			}
			else if("quit".equals(input)){
				connection.sendAction(new QuitDTO());
			}
			else
				System.out.println("Sorry, action not available!");	
		}		
	}

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(scanner);
		if(notify instanceof ClientGameOverNotify)
			scanner.close();
	}

	@Override
	public void welcome(String name) throws RemoteException {
		AddPlayerDTO actionDTO=new AddPlayerDTO(name);
		this.connection.sendAction(actionDTO);	
	}

	@Override
	public Object askForInput() {
		
		return scanner.nextLine();
	}

	@Override
	public void output(Object object) {
		
		System.out.println(object.toString());	
	}

}
