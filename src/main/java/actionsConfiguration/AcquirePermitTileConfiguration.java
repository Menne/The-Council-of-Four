package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.AcquirePermitTile;
import controller.AskParameterPack;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

public class AcquirePermitTileConfiguration extends ActionConfiguration{
	
	private AcquirePermitTile action;
	
	public AcquirePermitTileConfiguration(Game game, AcquirePermitTile action) {
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {
		
		List<String> parametersName=new ArrayList<String>();
		parametersName.add("Region where you want to acquire");
		parametersName.add("Permit tile you want to acquire");
		parametersName.add("Cards of your hand you want to use to satisfy the council");
		
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
		for(Integer i=0; i<=maxNumberOfCards; i++)
			cardsNumbers.add(i.toString());
		acceptableStrings.add(cardsNumbers);
						
		return new AskParameterPack(parametersName, acceptableStrings);
	}

	@Override
	public void setParameters(List<String> stringParameter) {
		this.action.setChosenRegion(regionTranslator
				(stringParameter.remove(0)));
		this.action.setNumberOfPermitTile(numberOfPermitTileTranslator
				(stringParameter.remove(0))); 
		this.action.setCardsToDescard(politicsCardsTranslator
				(stringParameter));	
	}
	
	private int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}
	
	private RegionBoard regionTranslator(String regionToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
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
	
	

}
