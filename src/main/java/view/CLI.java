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
	
	public void input(String input) {
		switch (input) {
			case "action":
				this.action();
				break;
			case "market":
				System.out.println("You can't access to the market at this moment");
				break;
			case "exit":
				System.out.println("Bye bye!");
				break;
			default:
				System.out.println("Sorry, I didn't understand");
		}
	}
	
	
	public void action() {
		System.out.println("Ok, you have choosed to make an action. Please select one "
				+ "from the following actions you can make");
		List<String> acceptableActions=this.parser.availableActions();
		for (String acceptableString : acceptableActions)
			System.out.println(acceptableString);
		
		Scanner scanner=new Scanner(System.in);
		String selectedAction=scanner.nextLine();
		while(!this.parser.cutActions(acceptableActions).contains(selectedAction)){
			System.out.println("Wrong action. Retry.");
			selectedAction=scanner.nextLine();
		}
		
		List<List<String>> acceptableParameters=this.parser.actionParser(selectedAction);
		List<String> stringParameters=new ArrayList<String>();
		for (List<String> currentListOfStrings : acceptableParameters) {
			System.out.println(currentListOfStrings.remove(0));
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
	}
	

	/**
	 * TODO
	 */
	public void stamp(Game game){
		System.out.println(game+" ");
	}

	


}