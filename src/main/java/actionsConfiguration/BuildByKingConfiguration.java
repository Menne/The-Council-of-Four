package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import controller.AskParameterPack;
import model.Game;
import model.actions.BuildByKing;
import model.gameTable.City;
import model.gameTable.CouncilBalcony;
import model.gameTable.Emporium;
import model.gameTable.PoliticsCard;
import model.gameTable.RegionBoard;

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
		parametersName.add("First card of your hand you want to use to satisfy the council");
		parametersName.add("Second card of your hand you want to use to satisfy the council. \n"
				+ "Attention: if you don't want to discard cards anymore, you just have to press x. \n"
				+ "If you press the same card you have discarded before, it will not be considered");
		parametersName.add("Third card of your hand you want to use to satisfy the council. \n"
				+ "Attention: if you don't want to discard cards anymore, you just have to press x. \n"
				+ "If you press the same card you have discarded before, it will not be considered");
		parametersName.add("Fourth card of your hand you want to use to satisfy the council. \n"
				+ "Attention: if you don't want to discard cards anymore, you just have to press x. \n"
				+ "If you press the same card you have discarded before, it will not be considered");
		
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
		for(Integer i=0; i<maxNumberOfCards; i++)
			cardsNumbers.add(i.toString());
		acceptableStrings.add(cardsNumbers);
		
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors()-1; i++) {
			List<String> cardsNumbersPlusExit=new ArrayList<String>();
			int maxNumberOfCardsPlusExit=this.game.getCurrentPlayer().getHand().size();
			for(Integer j=0; j<maxNumberOfCardsPlusExit; j++)
				cardsNumbersPlusExit.add(j.toString());
			cardsNumbersPlusExit.add("x");
			acceptableStrings.add(cardsNumbersPlusExit);
		}
		
		return new AskParameterPack(parametersName, acceptableStrings);
	}

	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setSelectedCity(cityTranslator
				(stringParameters.remove(0)));
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		cardsTranslated.add(politicsCardTranslator(stringParameters.remove(0)));
		for (String parameter : stringParameters)
			if (!parameter.equals("x"))
				cardsTranslated.add(politicsCardTranslator(parameter));
		this.action.setCardsToDescard(cardsTranslated);
	}
	
	private PoliticsCard politicsCardTranslator(String cardToTranslate) {
		Integer numberOfCard=Integer.parseInt(cardToTranslate);
		PoliticsCard cardTranslated=this.game.getCurrentPlayer().getHand().get(numberOfCard);
		return cardTranslated;
	}
	
	private City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}

}
