package controller;

import java.util.List;

import actions.AcquirePermitTile;
import actions.Action;
import actions.BuildByPermitTile;
import gameStuff.City;
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
	 *  crea e ritorna l'azione corrispondente
	 * @return
	 */
	private Action actionTranslator(){
		if(action.equals("acquire permit tile")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game) ;
				acquirePermitTile.set(this.parameters.remove(0)), 
				acquirePermitTile.set(this.parameters.remove(0)), 
				acquirePermitTile.(this.parameters);
			return acquirePermitTile;
		}
		if(action.equals("build by permit tile")) {
			Action buildByPermitTile=new BuildByPermitTile(this.game, 
					this.PermitTileTranslator(this.parameters.remove(0)), 
					this.cityTranslator(this.parameters.remove(0)));
			return buildByPermitTile;
		}
		if(action.equals("M3")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game, 
					this.regionTranslator(this.parameters.remove(0)), 
					this.numberOfPermitTileTranslator(this.parameters.remove(0)), 
					this.politicsCardsTranslator(this.parameters));
			return acquirePermitTile;
		}
		if(action.equals("M4")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game, 
					this.regionTranslator(this.parameters.remove(0)), 
					this.numberOfPermitTileTranslator(this.parameters.remove(0)), 
					this.politicsCardsTranslator(this.parameters));
			return acquirePermitTile;
		}
		if(action.equals("q1")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game, 
					this.regionTranslator(this.parameters.remove(0)), 
					this.numberOfPermitTileTranslator(this.parameters.remove(0)), 
					this.politicsCardsTranslator(this.parameters));
			return acquirePermitTile;
		}
		if(action.equals("2q")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game, 
					this.regionTranslator(this.parameters.remove(0)), 
					this.numberOfPermitTileTranslator(this.parameters.remove(0)), 
					this.politicsCardsTranslator(this.parameters));
			return acquirePermitTile;
		}
		if(action.equals("3q")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game, 
					this.regionTranslator(this.parameters.remove(0)), 
					this.numberOfPermitTileTranslator(this.parameters.remove(0)), 
					this.politicsCardsTranslator(this.parameters));
			return acquirePermitTile;
		}
		if(action.equals("4q")) {
			Action acquirePermitTile=new AcquirePermitTile(this.game, 
					this.regionTranslator(this.parameters.remove(0)), 
					this.numberOfPermitTileTranslator(this.parameters.remove(0)), 
					this.politicsCardsTranslator(this.parameters));
			return acquirePermitTile;
		}
		return null;
		
	}

	private PermitTile PermitTileTranslator(String permitTileToTranslate) {
		PermitTile permitTileTranslator = null;
		return permitTileTranslator;
	}

	private City cityTranslator(String cityToTranslate) {
		City cityTranslated = null;
		return cityTranslated;
	}

	private List<PoliticsCard> politicsCardsTranslator(List<String> cardsToTranslate) {
		List<PoliticsCard> cardsTranslated = null;
		return cardsTranslated;
		
	}

	private int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=0;
		return numberOfPermitTileTranslated;
		
	}

	private RegionBoard regionTranslator(String regionToTranslate) {
		RegionBoard regionTranslated=null;
		return regionTranslated;
	}
	
}
