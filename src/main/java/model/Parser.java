package model;

import java.util.ArrayList;
import java.util.List;

import model.gameTable.City;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.Emporium;
import model.gameTable.PermitTile;
import model.gameTable.PoliticsCard;
import model.gameTable.RegionBoard;

public class Parser {

	private final Game game;
	
	public Parser(Game game){
		this.game=game;
	}
	
	public List<List<String>> actionTranslator(String input) {
		switch (input) {
		case "m1": //eleggi consigliere
			return this.m1AcceptableParameters();
		case "m2": //acquista tessera permesso
			return this.m2AcceptableParameters();
		case "m3": //build an emporium with a permit tile
			return this.m3AcceptableParameters();
		case "m4": //build an emporium with king
			return this.m4AcceptableParameters();
		case "q1": //ingaggiare aiutante
			return this.q1AcceptableParameters();
		case "q2": //cambiare tessere permesso
			return this.q2AcceptableParameters();
		case "q3": //eleggi consigliere con aiutante
			return this.q3AcceptableParameters();
		case "q4": //azione principale aggiuntiva
			return this.q4AcceptableParameters();
		}
		return null;
	}



	private List<List<String>> m1AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableCouncillors());
		acceptableStrings.add(acceptableCouncilBalcony());
		return acceptableStrings;
	}

	private List<List<String>> m2AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableRegions());
		acceptableStrings.add(acceptableNumberOfPermitTile());
		acceptableStrings.add(acceptableFirstPoliticsCard());
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors(); i++)
			acceptableStrings.add(acceptablePoliticsCards());
		return acceptableStrings;
	}
	
	private List<List<String>> m3AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableCities());
		acceptableStrings.add(acceptablePermitTiles());
		return acceptableStrings;
	}
	
	private List<List<String>> m4AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableCities());
		acceptableStrings.add(acceptableFirstPoliticsCard());
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors(); i++)
			acceptableStrings.add(acceptablePoliticsCards());
		return acceptableStrings;
	}
	
	private List<List<String>> q1AcceptableParameters() {
		return null;
		// TODO Auto-generated method stub
		
	}

	private List<List<String>> q2AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableRegions());
		return acceptableStrings;
	}

	private  List<List<String>> q3AcceptableParameters() {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(acceptableCouncillors());
		acceptableStrings.add(acceptableCouncilBalcony());
		return acceptableStrings;
	}

	private List<List<String>> q4AcceptableParameters() {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	

	


	private List<String> acceptableNumberOfPermitTile() {
		List<String> acceptableNumber=new ArrayList<String>();
		acceptableNumber.add("Permit tile you want to acquire");
		acceptableNumber.add("0");
		acceptableNumber.add("1");
		return acceptableNumber;
	}

	private List<String> acceptableRegions() {
		List<String> acceptableRegionNames=new ArrayList<String>();
		acceptableRegionNames.add("Region where you want to acquire");
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			acceptableRegionNames.add(region.getName());
		return acceptableRegionNames;
	}

	private List<String> acceptableFirstPoliticsCard() {
		List<String> acceptableColours=new ArrayList<String>();
		acceptableColours.add("First card of your hand you want to use to satisfy the council");
		for(PoliticsCard card : this.game.getCurrentPlayer().getHand())
			acceptableColours.add(card.getColour().getColour());
		return acceptableColours;
	}
	
	private List<String> acceptablePoliticsCards() {
		List<String> acceptableColoursPlusExit=new ArrayList<String>();
		acceptableColoursPlusExit.add("Second card of your hand you want to use to satisfy the council. \n"
				+ "Attention: if you don't want to discard cards anymore, you just have to press x. \n"
				+ "If you press the same card you have discarded before, it will not be considered");		int maxNumberOfCardsPlusExit=this.game.getCurrentPlayer().getHand().size();
		for(Integer j=0; j<maxNumberOfCardsPlusExit; j++)
			acceptableColoursPlusExit.add(j.toString());
		acceptableColoursPlusExit.add("x");
		return acceptableColoursPlusExit;
	}
	
	private List<String> acceptableCouncillors() {
		List<String> councillorColours=new ArrayList<String>();
		councillorColours.add("Colour of the councillor which you want to pick");
		for (Councillor councillor : this.game.getGameTable().getCouncilReserve().getCouncillors())
			councillorColours.add(councillor.getColour().getColour());
		return councillorColours;
	}
	
	private List<String> acceptableCouncilBalcony() {
		List<String> acceptableRegionNames=new ArrayList<String>();
		acceptableRegionNames.add("Region where there is the council balcony in which you want to substitue a councillor");
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			acceptableRegionNames.add(region.getName());
		return acceptableRegionNames;
	}
	
	public List<String> acceptableCities() {
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
	
	private List<String> acceptablePermitTiles() {
		List<String> acceptableTiles=new ArrayList<String>();
		acceptableTiles.add("Permit tile you want to use to acquire");
		int maxNumberOfCards=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().size();
		for(Integer i=0; i<maxNumberOfCards; i++)
			acceptableTiles.add(i.toString());
		return acceptableTiles;
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
