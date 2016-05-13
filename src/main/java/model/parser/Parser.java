package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.gameTable.City;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.Emporium;
import model.gameTable.PermitTile;
import model.gameTable.PoliticsCard;
import model.gameTable.RegionBoard;

public class Parser {

	protected final Game game;
	private ActionParserVisitor selectedAction;
	
	public Parser(Game game){
		this.game=game;
		this.selectedAction=null;
	}
	
	public ActionParserVisitor getSelectedAction() {
		return selectedAction;
	}
	
	public List<List<String>> actionParser(String input) {
		switch (input) {
		case "m1":
			this.selectedAction=new ElectCouncillorParser();
			return this.selectedAction.acceptableParameters(this);
		case "m2":
			this.selectedAction=new AcquirePermitTileParser();
			return this.selectedAction.acceptableParameters(this);
		case "m3":
			this.selectedAction=new BuildWithPermitTileParser();
			return this.selectedAction.acceptableParameters(this);
		case "m4":
			this.selectedAction=new BuildWithKingParser();
			return this.selectedAction.acceptableParameters(this);
		case "q1":
			break;
		case "q2":
			this.selectedAction=new ChangePermitTilesParser();
			return this.selectedAction.acceptableParameters(this);
		case "q3":
			this.selectedAction=new ElectCouncillorByAssistantParser();
			return this.selectedAction.acceptableParameters(this);
		case "q4":
			break;
		}
		return null;
	}


	protected List<String> acceptableNumberOfPermitTile() {
		List<String> acceptableNumber=new ArrayList<String>();
		acceptableNumber.add("Permit tile you want to acquire");
		acceptableNumber.add("0");
		acceptableNumber.add("1");
		return acceptableNumber;
	}

	protected List<String> acceptableRegions() {
		List<String> acceptableRegionNames=new ArrayList<String>();
		acceptableRegionNames.add("Region where you want to acquire");
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			acceptableRegionNames.add(region.getName());
		return acceptableRegionNames;
	}

	protected List<String> acceptableFirstPoliticsCard() {
		List<String> acceptableColours=new ArrayList<String>();
		acceptableColours.add("First card of your hand you want to use to satisfy the council");
		for(PoliticsCard card : this.game.getCurrentPlayer().getHand())
			acceptableColours.add(card.getColour().getColour());
		return acceptableColours;
	}
	
	protected List<String> acceptablePoliticsCards() {
		List<String> acceptableColoursPlusExit=new ArrayList<String>();
		acceptableColoursPlusExit.add("Second card of your hand you want to use to satisfy the council. \n"
				+ "Attention: if you don't want to discard cards anymore, you just have to press x. \n"
				+ "If you press the same card you have discarded before, it will not be considered");		int maxNumberOfCardsPlusExit=this.game.getCurrentPlayer().getHand().size();
		for(Integer j=0; j<maxNumberOfCardsPlusExit; j++)
			acceptableColoursPlusExit.add(j.toString());
		acceptableColoursPlusExit.add("x");
		return acceptableColoursPlusExit;
	}
	
	protected List<String> acceptableCouncillors() {
		List<String> councillorColours=new ArrayList<String>();
		councillorColours.add("Colour of the councillor which you want to pick");
		for (Councillor councillor : this.game.getGameTable().getCouncilReserve().getCouncillors())
			councillorColours.add(councillor.getColour().getColour());
		return councillorColours;
	}
	
	protected List<String> acceptableCouncilBalcony() {
		List<String> acceptableRegionNames=new ArrayList<String>();
		acceptableRegionNames.add("Region where there is the council balcony in which you want to substitue a councillor");
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			acceptableRegionNames.add(region.getName());
		return acceptableRegionNames;
	}
	
	protected List<String> acceptableCities() {
		List<String> acceptableCityNames=new ArrayList<String>();
		acceptableCityNames.add("City where you want to build an emporium");
		for(City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getCityEmporiums().isEmpty())
				acceptableCityNames.add(city.getName());
			else
				for (Emporium emporium : city.getCityEmporiums())
					if (!emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
						acceptableCityNames.add(city.getName());
		return acceptableCityNames;
	}
	
	protected List<String> acceptablePermitTiles() {
		List<String> acceptableTiles=new ArrayList<String>();
		acceptableTiles.add("Permit tile you want to use to acquire");
		int maxNumberOfCards=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().size();
		for(Integer i=0; i<maxNumberOfCards; i++)
			acceptableTiles.add(i.toString());
		return acceptableTiles;
	}
		
	

	
	
	protected RegionBoard regionTranslator(String regionToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
	}
	
	protected int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}
	
	protected PermitTile permitTileTranslator(String permitTileToTranslate) {
		int numberOfPermitTile=Integer.parseInt(permitTileToTranslate);
		PermitTile permitTileTranslated=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(numberOfPermitTile);
		return permitTileTranslated;
	}
	
	protected PoliticsCard politicsCardTranslator(String cardToTranslate) {
		Integer numberOfCard=Integer.parseInt(cardToTranslate);
		PoliticsCard cardTranslated=this.game.getCurrentPlayer().getHand().get(numberOfCard);
		return cardTranslated;
	}
	
	protected City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}
	
	protected Councillor councillorTranslator(String newCouncillorToTranslate) {
		for (Councillor newCouncillorTranslated : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if (newCouncillorTranslated.getColour().getColour().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		throw new IllegalArgumentException("newCouncillorToTranslate is not a colour name");
	}
	
	protected CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(councilBalconyToTranslate.equals(region.getName()))
				return region.getRegionBalcony();
		throw new IllegalArgumentException("councilBalconyToTranslate is not a region name");
	}
	
}
