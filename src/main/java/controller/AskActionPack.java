package controller;

import java.util.List;

import model.Game;

public class AskActionPack {

	private final Game game;
	private final List<String> acceptableString;
	
	public AskActionPack(Game game, List<String> acceptableString){
		this.acceptableString=acceptableString;
		this.game=game;
	}

	public Game getGame() {
		return game;
	}

	public List<String> getAcceptableString() {
		return acceptableString;
	}
	
	
}
