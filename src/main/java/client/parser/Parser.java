package client.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import client.ModelDTO.CardColourDTO;
import client.ModelDTO.CityDTO;
import client.ModelDTO.GameDTO;
import client.ModelDTO.PermitTileDTO;
import client.ModelDTO.PlayerDTO;
import client.ModelDTO.RegionDTO;
import client.actionDTO.ActionDTO;
import client.clientView.notifies.ClientErrorNotify;
import model.Game;
import model.actions.Action;
import model.gameTable.CardColour;
import model.gameTable.City;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.Emporium;
import model.gameTable.PermitTile;
import model.gameTable.PoliticsCard;
import model.gameTable.RegionBoard;
import view.ErrorNotify;

public class Parser {

	protected final GameDTO game;
	
	/**
	 * Constructor of the parser
	 * @param game is the current game
	 */
	public Parser(GameDTO game){
		this.game=game;
	}

	
	/**
	 * This method gets from the current state of play the list of available actions and convert them to strings,
	 * then cuts the initials of available actions to let the user choose more easily
	 * @return the list of strings of initials
	 */
	public List<String> availableActions() {
		List<String> acceptableStrings=new ArrayList<String>();
		for (ActionDTO action : this.game.getAvailableActions())
			acceptableStrings.add(action.toString().substring(0,2));
		return acceptableStrings;
	}
	
	
	
	/**
	 * This method reads the string inserted by the user and returns the corresponding action
	 * @return the corresponding available action
	 */
	public ActionDTO actionParser(String selectedAction) {
		for (ActionDTO availableAction : this.game.getAvailableActions())
			if (selectedAction.equals(availableAction.toString().substring(0,2)))
				return availableAction;
		return null;
	}
	
	/**
	 * This method translates the strings of parameters into effective parameters and sets them to the action
	 * @param selectedAction is the action without parameters set
	 * @return the selected action with the parameters set
	 */
	public ActionDTO parametersParser(ActionDTO selectedAction) {
		return selectedAction.setParser(this.game).setParameters(this);
	}
	
	
	//Now there are the translators from string to effective parameter
	
	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptableNumberOfPermitTile() {
		List<String> acceptableNumber=new ArrayList<String>();
		acceptableNumber.add("0");
		acceptableNumber.add("1");
		return acceptableNumber;
	}

	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptableRegions() {
		List<String> acceptableRegionNames=new ArrayList<String>();
		for (RegionDTO region : this.game.getClientRegions())
			acceptableRegionNames.add(region.getName());
		return acceptableRegionNames;
	}
	
	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptablePoliticsCards() {
		List<String> acceptableColours=new ArrayList<String>();	
		for(CardColourDTO card : this.game.getCurrentPlayer().getHand())
			acceptableColours.add(card.getName());
		if (acceptableColours.isEmpty())
			this.game.notifyObserver(new ClientErrorNotify("It seems that your hand is empty..."));
		return acceptableColours;
	}
	
	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptableCouncillors() {
		List<String> councillorColours=new ArrayList<String>();
		for (CardColourDTO councillor : this.game.getClientCouncillorReserve())
			councillorColours.add(councillor.getName());
		return councillorColours;
	}
	
	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptableCouncilBalcony() {
		List<String> acceptableRegionNames=new ArrayList<String>();
		for (RegionDTO region : this.game.getClientRegions())
			acceptableRegionNames.add(region.getName());
		acceptableRegionNames.add("kingcouncil");
		return acceptableRegionNames;
	}
	
	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptableCities() {
		List<String> acceptableCityNames=new ArrayList<String>();
		for (RegionDTO region : this.game.getClientRegions())
			for (CityDTO city : region.getCities())
				if(city.getBuildedEmporiums().isEmpty())
					acceptableCityNames.add(city.getName());
				else
					for (PlayerDTO emporium : city.getBuildedEmporiums())
						if (!emporium.equals(this.game.getCurrentPlayer()))
							acceptableCityNames.add(city.getName());
		return acceptableCityNames;
	}
	
	/**
	 * This method builds a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return which contains the the list of possible strings for the selected action,
	 */
	protected List<String> acceptablePermitTiles() {
		List<String> acceptableTiles=new ArrayList<String>();
		int maxNumberOfCards=this.game.getCurrentPlayer().getAvailablePermitTiles().size();
		for(Integer i=0; i<maxNumberOfCards; i++)
			acceptableTiles.add(i.toString());
		if (acceptableTiles.isEmpty())
			this.game.notifyObserver(new ClientErrorNotify("It seems that you haven't permit tiles..."));
		return acceptableTiles;
	}
		
	
	//Now there are the methods which get the possible parameters from the current game
	
	/**
	 * Translates the string given from the user into the corresponding region board
	 * @param regionToTranslate is the string corresponding to the name of the selected region board
	 * @return the region board obtained from the string
	 */
	protected RegionDTO regionTranslator(String regionToTranslate) {
		for(RegionDTO region : this.game.getClientRegions())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
	}
	
	/**
	 * Translates the string given from the user into the corresponding number of permit tile
	 * @param numberOfPermitTileToTranslate is the string corresponding to the index of the permit tile in the selected region
	 * @return the number of permit tile obtained from the string
	 */
	protected int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}
	
	/**
	 * translates the string given from the user into the corresponding permit tile
	 * @param permitTileToTranslate is the string corresponding to the index of the permit tile in the hand of the player
	 * @return the permit tile obtained from the string
	 */
	/*protected PermitTileDTO permitTileTranslator(String permitTileToTranslate) {
		int numberOfPermitTile=Integer.parseInt(permitTileToTranslate);
		PermitTileDTO permitTileTranslated=this.game.getCurrentPlayer().getAvailablePermitTiles().get(numberOfPermitTile);
		return permitTileTranslated;
	}*/
	
	/**
	 * Translates the string given from the user into the corresponding politics card
	 * @param cardToTranslate is the string corresponding to the colour of the politics card in the hand of the player
	 * @return the politics card obtained from the string
	 */
	protected List<CardColourDTO> politicsCardsTranslator(String cardsToTranslate) {
		List<CardColourDTO> cardsTranslated=new ArrayList<CardColourDTO>();
		StringTokenizer st = new StringTokenizer(cardsToTranslate);
	    while (st.hasMoreTokens())
	    	for (CardColourDTO cardTranslated : this.game.getCurrentPlayer().getHand())
	    		if (cardTranslated.getName().equals(st))
	    			cardsTranslated.add(cardTranslated);
		return cardsTranslated;
	}

	
	/**
	 * Translates the string given from the user into the corresponding city
	 * @param cityToTranslate is the string corresponding to the name of the selected city
	 * @return the city obtained from the string
	 */
	protected CityDTO cityTranslator(String cityToTranslate) {
		for (RegionDTO regionBoard : this.game.getClientRegions())
			for (CityDTO cityTranslated : regionBoard.getCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}
	
	/**
	 * Translates the string given from the user into the corresponding councillor
	 * @param newCouncillorToTranslate is the string corresponding to the colour of the selected councillor
	 * @return the councillor obtained from the string
	 */
	protected CardColourDTO councillorTranslator(String newCouncillorToTranslate) {
		for (CardColourDTO newCouncillorTranslated : this.game.getClientCouncillorReserve())
			if (newCouncillorTranslated.getName().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		throw new IllegalArgumentException("newCouncillorToTranslate is not a colour name");
	}
	
	/**
	 * Translates the string given from the user into the corresponding council balcony
	 * @param councilBalconyToTranslate is the string corresponding to the name of the region where there is the selected council balcony,
	 * or corresponding to the council of king
	 * @return the council balcony obtained from the string
	 */
	protected CardColourDTO[] councilBalconyTranslator(String councilBalconyToTranslate) {
		for(RegionDTO region : this.game.getClientRegions())
			if(councilBalconyToTranslate.equals(region.getName()))
				return region.getBalcony();
			else
				if (councilBalconyToTranslate.equals("kingcouncil"))
					return this.game.getClientKingBalcony();
		throw new IllegalArgumentException("councilBalconyToTranslate is not a region name");
	}
	
}
