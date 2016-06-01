package client.view.socket;

import java.util.Scanner;

import client.view.ClientView;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.parser.Parser;


public class CLI extends ClientView{

	private final Parser parser;
	private Scanner scanner;
	
	public CLI(Parser parser) {
		this.parser=parser;
		this.scanner=new Scanner(System.in);
	}
	
	public Scanner getScanner() {
		return this.scanner;
	}

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(this);
	}
	
	
	public void input() {
		while (true) {

			String input=this.scanner.nextLine();
			if (this.parser.availableActions().contains(input)) {
				ActionDTO selectedAction=this.parser.actionParser(input);
				this.checkIfParametersNeeded(selectedAction);
			}
			else
				System.out.println("Sorry, action not available!");	
		}
	}
	
	public void checkIfParametersNeeded(ActionDTO selectedAction) {
		if (selectedAction instanceof ActionWithParameters) {
			ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
			this.insertParameters(actionWithParameters);
		}
		else
			this.notifyObserver(selectedAction);
	}
	
	public void insertParameters(ActionWithParameters selectedAction) {
		this.parser.parametersParser(selectedAction);
		if (selectedAction.checkIfParametersSetted())
			this.notifyObserver(selectedAction);
	}
	

}
