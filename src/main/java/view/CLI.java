package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AskActionPack;
import controller.AskParameterPack;
import controller.GameLogic;
import controller.Turn;
import model.Game;
import observerPattern.Observable;

public class CLI extends View{

	public CLI(GameLogic gameLogic) {
		super(gameLogic);
	}

	@Override
	public <C> void update(Observable o, C change) {
		Scanner scanner=new Scanner(System.in);
		if(change instanceof AskActionPack){
			AskActionPack notify=(AskActionPack) change;
			stamp(notify.getGame());
			//richiesta all'utente dell'azione
			System.out.println("Player "+notify.getGame().getCurrentPlayer().getName()+
					", it's your turn!\n Choose one of the following actions:\n ");
			for(String action : notify.getAcceptableString())
				System.out.println(action);
			String selectedAction=scanner.nextLine();
			while(!notify.getAcceptableString().contains(selectedAction)){
				System.out.println("You have choosen a wrong action. Retry");
				selectedAction=scanner.nextLine();
				}
			//preparo give action pack
			GiveActionPack gap=new GiveActionPack(selectedAction);
			
			//invio give action pack
			notifyObservers(gap);							
		}
		if(change instanceof AskParameterPack){
			AskParameterPack notify=(AskParameterPack) change;
			//chiedo all'utente di inserire i parametri e li salvo in una lista
			List<String> selectedParameters=new ArrayList<String>();
			for(int i=0; i<notify.getParameters().size(); i++){
				System.out.println("Insert Parameter: "+notify.getParameters().get(i)+
						". Chose among:\n");
				for(String possibleString : notify.getAcceptableParameters().get(i))
					System.out.println(possibleString);
				String parameter=scanner.nextLine();
				while(!notify.getAcceptableParameters().get(i).contains(parameter)){
					System.out.println("Wrong parameter. Retry.");
					parameter=scanner.nextLine();
				}
				selectedParameters.add(parameter);
			}
			
			//preparo pacchetto
			GiveParameterPack gpp = new GiveParameterPack(selectedParameters);
			notifyObservers(gpp);
		}
		scanner.close();
	}
	
	/**
	 * TODO
	 */
	public void stamp(Game game){
		System.out.println(game);
	}


}