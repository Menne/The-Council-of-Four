package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.BuildByKing;
import controller.AskParameterPack;
import gameStuff.City;
import gameStuff.Emporium;
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
		
		List<String> parametersName=new ArrayList<String>();
		parametersName.add("City where you want to build an emporium");
		parametersName.add("Cards of your hand you want to use to satisfy the council");
		
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		
		List<String> cityNames=new ArrayList<String>();
		for(City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getCityEmporiums().isEmpty())
				cityNames.add(city.getName());
			else
				for (Emporium emporium : city.getCityEmporiums())
					if (!emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
						cityNames.add(city.getName());
		acceptableStrings.add(cityNames);
		
		List<String> cardsNumbers=new ArrayList<String>();
		int maxNumberOfCards=this.game.getCurrentPlayer().getHand().size();
		for(Integer i=0; i<=maxNumberOfCards; i++)
			cardsNumbers.add(i.toString());
		acceptableStrings.add(cardsNumbers);
						
		return new AskParameterPack(parametersName, acceptableStrings);
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
