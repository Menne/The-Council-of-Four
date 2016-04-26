package view;

import controller.Controller;
import observerPattern.Observable;

public class CLI extends View{
	
	public CLI(Controller controller){
		super(controller);
		System.out.println(controller.getGame());
	}

	@Override
	public <C> void update(Observable o, C change) {
		// TODO Auto-generated method stub
		
	}
	
}