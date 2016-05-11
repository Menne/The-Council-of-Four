package packdaeliminare;

import java.util.ArrayList;
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
	
	public List<String> cutStrings() {
		List<String> stringsCutted=new ArrayList<String>();
		for (String stringToCut : this.acceptableString)
			stringsCutted.add(stringToCut.substring(0, 2));
		return stringsCutted;
	}
	
	
}
