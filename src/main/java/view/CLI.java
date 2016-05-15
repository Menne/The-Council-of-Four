package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Game;
import model.parser.Parser;


public class CLI extends View{

	private final Parser parser;
	private final Scanner scanner;
	
	public CLI(Game game, Parser parser, Scanner scanner) {
		super(game);
		this.parser=parser;
		this.scanner=scanner;
	}
	
	
	@Override
	public void update(ViewNotify notify) {
		notify.stamp(this);
	}

	
	public void input(String input) {
		if (this.parser.availableActions().contains(input)) {
			this.insertParameters(input);
		}
		else
			System.out.println("Sorry, action not available!");	
	}
	
	public boolean insertParameters(String selectedAction) {
//		Scanner scanner=new Scanner(System.in);
		
		//the list of parameters that that the action needs
		List<String> stringParameters=new ArrayList<String>();
		
		//for each parameter that the action needs, this list contains the list of acceptable strings
		List<List<String>> acceptableParameters=this.parser.actionParser(selectedAction);
		
		System.out.println(acceptableParameters.remove(0));
		
		if (acceptableParameters.isEmpty()) {
//			scanner.close();
			notifyObserver(this.parser.getCurrentParser().parametersParser(stringParameters, this.parser));
			return true;
		}
		for (List<String> currentListOfStrings : acceptableParameters) {
			System.out.println(currentListOfStrings.remove(0));
			if (currentListOfStrings.isEmpty()) {
				System.out.println("Sorry, but you can't do this action. "
						+ "Check out your current game state!");
//				scanner.close();
				return false;
			}
			for (String currentString : currentListOfStrings) 
				System.out.print(currentString + "\t");
			String parameter=scanner.nextLine();
			while(!currentListOfStrings.contains(parameter)){
				System.out.println("Wrong parameter. Retry.");
				parameter=scanner.nextLine();
			}
			stringParameters.add(parameter);
		}
//		scanner.close();
		notifyObserver(this.parser.getCurrentParser().parametersParser(stringParameters, this.parser));
		return true;
	}
	

	


}