package controller;

import java.util.List;

import actions.Action;
import model.Game;

public class ActionExecutor {

	private final Game game;
	private final String action;
	private final List<String> parameters;
	
	public ActionExecutor(Game game, String action, List<String> parameters){
		this.action=action;
		this.parameters=parameters;
		this.game=game;
	}
	
	public void callAction(){
//		Action action=this.actionTransaltor();
//		action.executeAction();
	}
	
	/**
	 * TODO riceve azione e parametro sottoforma di stringa,
	 *  crea e ritorna l'azione corrispondente
	 * @return
	 
	private Action actionTransaltor(){
		if(action.equals("M1"))
	}
	
	**/
}
