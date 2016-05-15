package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Game;
import model.parser.Parser;


public class CLI extends View{

	private final Parser parser;
	
	public CLI(Game game, Parser parser) {
		super(game);
		this.parser=parser;
	}
	
	
	@Override
	public void update(ViewNotify notify) {
		notify.stamp(this);
	}

	
	public boolean input(String input) {
		if (this.parser.availableActions().contains(input)) {
			this.insertParameters(input);
			return true;
		}
		else
			return false;
	}
	
	public boolean insertParameters(String selectedAction) {
		Scanner scanner=new Scanner(System.in);
		List<List<String>> acceptableParameters=this.parser.actionParser(selectedAction);
		List<String> stringParameters=new ArrayList<String>();
		System.out.println(acceptableParameters.remove(0));
		if (acceptableParameters.isEmpty())
			notifyObserver(this.parser.getCurrentParser().parametersParser(stringParameters, this.parser));
		for (List<String> currentListOfStrings : acceptableParameters) {
			System.out.println(currentListOfStrings.remove(0));
			if (currentListOfStrings.isEmpty()) {
				System.out.println("Sorry, but you can't do this action. "
						+ "Check out your current game state!");
				scanner.close();
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
		scanner.close();
		notifyObserver(this.parser.getCurrentParser().parametersParser(stringParameters, this.parser));
		return true;
	}
	

	/**
	 * TODO
	 */
	public void stamp(Game game){
		System.out.println(game+" ");
	}

	


}