package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.AcquirePermitTile;
import controller.AskParameterPack;
import gameStuff.CouncilBalcony;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class defines the possible strings corresponding to the parameters 
 * necessary for the action acquire permit tile and gives them to the CLI. 
 * In a second moment, it receives the input from the CLI and translates it 
 * into the effective parameters required from the action acquire permit tile
 * @author Emanuele
 *
 */

public class AcquirePermitTileConfiguration extends ActionConfiguration{
	
	private AcquirePermitTile action;
	
	/**
	 * Constructor of AcquirePermitTileConfiguration
	 * @param game is the current game
	 * @param action is the action which requires to be configured with parameters given by the user
	 */
	public AcquirePermitTileConfiguration(Game game, AcquirePermitTile action) {
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
		parametersName.add("Region where you want to acquire");
		parametersName.add("Permit tile you want to acquire");
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
		
		List<String> regionNames=new ArrayList<String>();
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			regionNames.add(region.getName());
		acceptableStrings.add(regionNames);
		
		List<String> permitTileNumbers=new ArrayList<String>();
		permitTileNumbers.add("0");
		permitTileNumbers.add("1");
		acceptableStrings.add(permitTileNumbers);
		
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
		this.action.setChosenRegion(regionTranslator
				(stringParameters.remove(0)));
		this.action.setNumberOfPermitTile(numberOfPermitTileTranslator
				(stringParameters.remove(0))); 
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		cardsTranslated.add(politicsCardTranslator(stringParameters.remove(0)));
		for (String parameter : stringParameters)
			if (!parameter.equals("x"))
				cardsTranslated.add(politicsCardTranslator(parameter));
		this.action.setCardsToDescard(cardsTranslated);
	}
	
	/**
	 * translates the string given from the user into the corresponding number of permit tile
	 * @param numberOfPermitTileToTranslate is the string corresponding to the index of the permit tile in the selected region
	 * @return the number of permit tile obtained from the string
	 */
	private int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}
	
	/**
	 * translates the string given from the user into the corresponding region board
	 * @param regionToTranslate is the string corresponding to the name of the selected region board
	 * @return the region board obtained from the string
	 */
	private RegionBoard regionTranslator(String regionToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
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
	
}