package view;

import java.util.Scanner;

import model.Game;
import model.actions.Action;
import model.actions.NeedParameters;
import model.parser.Parser;


public class CLI extends View{

	private final Parser parser;
	private final Scanner scanner;
	
	public CLI(Game game, Parser parser, Scanner scanner) {
		super(game);
		this.parser=parser;
		this.scanner=scanner;
	}
	
	
	public Scanner getScanner() {
		return scanner;
	}


	@Override
	public void update(ViewNotify notify) {
		notify.stamp(this);
	}

	
	public void input(String input) {
		if (this.parser.availableActions().contains(input)) {
			Action selectedAction=this.parser.actionParser(input);
			this.checkIfParametersNeeded(selectedAction);
		}
		else
			System.out.println("Sorry, action not available!");	
	}
	
	public void checkIfParametersNeeded(Action selectedAction) {
		if (selectedAction instanceof NeedParameters)
			this.insertParameters(selectedAction);
		else
			notifyObserver(selectedAction);
	}
	
	
	public void insertParameters(Action selectedAction) {
		this.parser.parametersParser(selectedAction);
		notifyObserver(selectedAction);
	}
	

	


}