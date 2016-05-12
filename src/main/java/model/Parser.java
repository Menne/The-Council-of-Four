package model;

import java.util.ArrayList;
import java.util.List;

import model.gameTable.City;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.PermitTile;
import model.gameTable.PoliticsCard;
import model.gameTable.RegionBoard;

public class Parser {

	private final Game game;
	
	public Parser(Game game){
		this.game=game;
	}
	
	
	public void actionTranslator(String input) {
		switch (input) {
		case "m1":
			this.m1AcceptableParameters();
			break;
		case "m2":
			this.m2AcceptableParameters();
			break;
		case "m3":
			this.m3AcceptableParameters();
			break;
		case "m4":
			this.m4AcceptableParameters();
			break;
		case "q1":
			this.q1AcceptableParameters();
			break;
		case "q2":
			this.q2AcceptableParameters();
			break;
		case "q3":
			this.q3AcceptableParameters();
			break;
		case "q4":
			this.q4AcceptableParameters();
		}
	}

	private List<List<String>> m1AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableRegions());
		acceptableStrings.add(acceptableNumberOfPermitTile());
		return acceptableStrings;
	}
	
	private List<List<String>> m2AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableRegions());
		acceptableStrings.add(acceptableNumberOfPermitTile());
		return acceptableStrings;
	}

	private List<String> acceptableNumberOfPermitTile() {
		List<String> acceptableStrings=new ArrayList<String>();
		acceptableStrings.add("Permit tile you want to acquire");
		acceptableStrings.add("0");
		acceptableStrings.add("1");
		return acceptableStrings;
	}

	private List<String> acceptableRegions() {
		List<String> acceptableStrings=new ArrayList<String>();
		acceptableStrings.add("Region where you want to acquire");
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			acceptableStrings.add(region.getName());
		return acceptableStrings;
	}

	private List<String> acceptablePoliticsCards() {
		List<String> acceptableStrings=new ArrayList<String>();
		acceptableStrings.add("First card of your hand you want to use to satisfy the council");
		for (PoliticsCard card : this.game.getCurrentPlayer().getHand())
			acceptableStrings.add(card.getColour().getColour());
		return acceptableStrings;
	}


		
		
		
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors()-1; i++) {
			List<String> cardsNumbersPlusExit=new ArrayList<String>();
			int maxNumberOfCardsPlusExit=this.game.getCurrentPlayer().getHand().size();
			for(Integer j=0; j<maxNumberOfCardsPlusExit; j++)
				cardsNumbersPlusExit.add(j.toString());
			cardsNumbersPlusExit.add("x");
			acceptableStrings.add(cardsNumbersPlusExit);
		}
		return acceptableStrings;
		
	}
	
	private PoliticsCard politicsCardParser(String cardToTranslate) {
		Integer numberOfCard=Integer.parseInt(cardToTranslate);
		PoliticsCard cardTranslated=this.game.getCurrentPlayer().getHand().get(numberOfCard);
		return cardTranslated;
	}
	
	private RegionBoard regionTranslator(String regionToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
	}
	
	private int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}
	
	private PermitTile permitTileTranslator(String permitTileToTranslate) {
		int numberOfPermitTile=Integer.parseInt(permitTileToTranslate);
		PermitTile permitTileTranslated=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(numberOfPermitTile);
		return permitTileTranslated;
	}
	
	private City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}
	
	private Councillor councillorTranslator(String newCouncillorToTranslate) {
		for (Councillor newCouncillorTranslated : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if (newCouncillorTranslated.getColour().getColour().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		throw new IllegalArgumentException("newCouncillorToTranslate is not a colour name");
	}
	
	private CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(councilBalconyToTranslate.equals(region.getName()))
				return region.getRegionBalcony();
		throw new IllegalArgumentException("councilBalconyToTranslate is not a region name");
	}
	
}
