package view;

import controller.Controller;
import model.Game;
import model.GameState;
import observerPattern.Observable;

public class CLI extends View{
	
	public CLI(Controller controller){
		super(controller);
		System.out.println(controller.getGame());
	}


	@Override
	public <C> void update(Observable o, C change) {
		if(change instanceof GameState){
			System.out.println("PARTITA FINITA");
			System.exit(0);
		}			
		else{
			Game game=(Game) change;
			System.out.println(game);
		}						
	}


	@Override
	public <I> void input(I input) {
		this.notifyObservers(input);		
	}
	
}