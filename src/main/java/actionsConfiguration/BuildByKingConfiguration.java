package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.BuildByKing;
import controller.AskParameterPack;
import gameStuff.City;
import gameStuff.CouncilBalcony;
import gameStuff.Emporium;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class defines the possible strings corresponding to the parameters 
 * necessary for the action build by king and gives them to the CLI. 
 * In a second moment, it receives the input from the CLI and translates it 
 * into the effective parameters required from the action build by king
 * @author Emanuele
 *
 */

public class BuildByKingConfiguration extends ActionConfiguration{
	
	private BuildByKing action;
	
	/**
	 * Constructor of BuildByKingConfiguration
	 * @param game is the current game
	 * @param action is the action which requires to be configured with parameters given by the user
	 */
	public BuildByKingConfiguration(Game game, BuildByKing action){
		super(game);
		this.action=action;
	}

	/**
	 * This method packs in a AskParameterPack a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return a pack to be sent to CLI, which contains the instructions 
	 * the user can follow to insert the parameters, and the list of possible strings for the selected action
	 */
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

	/**
	 * This method sets to the selected action the parameters given by the user, 
	 * after a translation from the input, which is a list of strings
	 */
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
	
	/**
	 * translates the string given from the user into the corresponding politics card
	 * @param cardToTranslate is the string corresponding to the index of the politics card in the hand of the player
	 * @return the politics card obtained from the string
	 */
	private PoliticsCard politicsCardTranslator(String cardToTranslate) {
		Integer numberOfCard=Integer.parseInt(cardToTranslate);
		PoliticsCard cardTranslated=this.game.getCurrentPlayer().getHand().get(numberOfCard);
		return cardTranslated;
	}
	
	/**
	 * translates the string given from the user into the corresponding city
	 * @param cityToTranslate is the string corresponding to the name of the selected city
	 * @return the city obtained from the string
	 */
	private City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}

}
