package client.clientView;

import java.util.Scanner;

import client.ModelDTO.GameDTO;
import client.actionDTO.ActionDTO;
import client.actionDTO.NeedParameters;
import client.clientView.notifies.ClientViewNotify;
import client.parser.Parser;


public class CLI extends ClientView{

	private static GameDTO clientGame;
	private final Parser parser;
	private Scanner scanner;
	
	public CLI(Parser parser) {
		super(clientGame);
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
		if (selectedAction instanceof NeedParameters)
			this.insertParameters(selectedAction);
		else
			notifyObserver(selectedAction);
	}
	
	public void insertParameters(ActionDTO selectedAction) {
		this.parser.parametersParser(selectedAction);
		notifyObserver(selectedAction);
	}
	

}
