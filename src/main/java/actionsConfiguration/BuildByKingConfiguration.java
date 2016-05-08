package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.BuildByKing;
import controller.AskParameterPack;
import gameStuff.City;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

public class BuildByKingConfiguration extends ActionConfiguration{
	
	private BuildByKing action;
	
	public BuildByKingConfiguration(Game game, BuildByKing action){
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setSelectedCity(cityTranslator
				(stringParameters.remove(0)));
		this.action.setCardsToDescard(politicsCardsTranslator
				(stringParameters));
		
	}
	
	private List<PoliticsCard> politicsCardsTranslator(List<String> cardsToTranslate) {
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		Integer numberOfCard;
		for (String card : cardsToTranslate) {
			numberOfCard=Integer.parseInt(card); 
			cardsTranslated.add(this.game.getCurrentPlayer().getHand().get(numberOfCard));
		}
		return cardsTranslated;
	}
	
	private City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}

}
