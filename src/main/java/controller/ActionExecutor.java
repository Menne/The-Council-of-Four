package controller;

import java.util.ArrayList;
import java.util.List;

import actions.AcquirePermitTile;
import actions.Action;
import actions.AdditionalMainAction;
import actions.BuildByKing;
import actions.BuildByPermitTile;
import actions.ChangePermitTiles;
import actions.ElectCouncillor;
import actions.ElectCouncillorByAssistant;
import actions.EngageAssistant;
import gameStuff.City;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.PermitTile;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

public class ActionExecutor {

	private final Game game;
	private final String action;
	private final List<String> parameters;
	
	public ActionExecutor(Game game, String action, List<String> parameters){
		this.action=action;
		this.parameters=parameters;
		this.game=game;
	}
	
	public void callAction(){
		Action action=this.actionTranslator();
		action.executeAction();
	}
	
	/**
	 * TODO riceve azione e parametro sottoforma di stringa,
	 * crea e ritorna l'azione corrispondente
	 * @return
	 */
	private Action actionTranslator(){
		if(action.equals("elect councillor")) {
			ElectCouncillor electCouncillor=new ElectCouncillor(this.game);
			electCouncillor.setNewCouncillor(councillorTranslator
					(this.parameters.remove(0)));
			electCouncillor.setCouncilBalcony(councilBalconyTranslator
					(this.parameters.remove(0)));
			return electCouncillor;
		}
		if(action.equals("acquire permit tile")) {
			AcquirePermitTile acquirePermitTile=new AcquirePermitTile(this.game);
			acquirePermitTile.setChosenRegion(regionTranslator
					(this.parameters.remove(0)));
			acquirePermitTile.setNumberOfPermitTile(numberOfPermitTileTranslator
					(this.parameters.remove(0))); 
			acquirePermitTile.setCardsToDescard(politicsCardsTranslator
					(this.parameters));
			return acquirePermitTile;
		}
		if(action.equals("build by permit tile")) {
			BuildByPermitTile buildByPermitTile=new BuildByPermitTile(this.game);
			buildByPermitTile.setSelectedCity(cityTranslator
					(this.parameters.remove(0)));
			buildByPermitTile.setSelectedPermitTile(permitTileTranslator
					(this.parameters.remove(0)));
			return buildByPermitTile;
		}
		if(action.equals("build by king")) {
			BuildByKing buildByKing=new BuildByKing(this.game);
			buildByKing.setSelectedCity(cityTranslator
					(this.parameters.remove(0)));
			buildByKing.setCardsToDescard(politicsCardsTranslator
					(this.parameters));
			return buildByKing;
		}
		if(action.equals("engage assistant")) {
			EngageAssistant engageAssistant=new EngageAssistant(this.game);
			return engageAssistant;
		}
		if(action.equals("change permit tiles")) {
			ChangePermitTiles changePermitTiles=new ChangePermitTiles(this.game);
			changePermitTiles.setSelectedRegion(regionTranslator
					(this.parameters.remove(0)));
			return changePermitTiles;
		}
		if(action.equals("elect councillor by assistant")) {
			ElectCouncillorByAssistant electCouncillorByAssistant=new ElectCouncillorByAssistant(this.game);
			electCouncillorByAssistant.setNewCouncillor(councillorTranslator
					(this.parameters.remove(0)));
			electCouncillorByAssistant.setCouncilBalcony(councilBalconyTranslator
					(this.parameters.remove(0)));
			return electCouncillorByAssistant;
		}
		if(action.equals("additional main action")) {
			AdditionalMainAction additionalMainAction=new AdditionalMainAction(this.game);
			return additionalMainAction;
		}
		return null;
		
	}

	private CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		CouncilBalcony councilBalconyTranslator = null;
		return councilBalconyTranslator;
	}

	private Councillor councillorTranslator(String newCouncillorToTranslate) {
		Councillor newCouncillorTranslated = null;
		return newCouncillorTranslated;
	}

	private PermitTile permitTileTranslator(String permitTileToTranslate) {
		PermitTile permitTileTranslator = null;
		return permitTileTranslator;
	}

	private City cityTranslator(String cityToTranslate) {
		City cityTranslated = null;
		return cityTranslated;
	}

	private List<PoliticsCard> politicsCardsTranslator(List<String> cardsToTranslate) {
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		
		return cardsTranslated;
	}

	private int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}

	private RegionBoard regionTranslator(String regionToTranslate) {
		RegionBoard regionTranslated=null;
		return regionTranslated;
	}
	
}
