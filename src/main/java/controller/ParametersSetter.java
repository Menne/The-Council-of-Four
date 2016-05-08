package controller;

import java.util.ArrayList;
import java.util.List;

import actions.AcquirePermitTile;
import actions.Action;
import actions.BuildByKing;
import actions.BuildByPermitTile;
import actions.ChangePermitTiles;
import actions.ElectCouncillor;
import actions.ElectCouncillorByAssistant;
import gameStuff.City;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.PermitTile;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

public class ParametersSetter {

	private final Game game;
	private final Action action;
	private final List<String> parameters;
	
	public ParametersSetter(Game game, Action action, List<String> parameters){
		this.action=action;
		this.parameters=parameters;
		this.game=game;
	}
	
	/**
	 * TODO riceve azione e parametro sottoforma di stringa,
	 * crea e ritorna l'azione corrispondente
	 * @return
	 */
	private void parametersSetter(){
		if (this.action instanceof ElectCouncillor) {
			ElectCouncillor selectedAction=(ElectCouncillor) this.action;
			selectedAction.setNewCouncillor(councillorTranslator
					(this.parameters.remove(0)));
			selectedAction.setCouncilBalcony(councilBalconyTranslator
					(this.parameters.remove(0)));
		}
		if (this.action instanceof AcquirePermitTile) {
			AcquirePermitTile selectedAction=(AcquirePermitTile) this.action;
			selectedAction.setChosenRegion(regionTranslator
					(this.parameters.remove(0)));
			selectedAction.setNumberOfPermitTile(numberOfPermitTileTranslator
					(this.parameters.remove(0))); 
			selectedAction.setCardsToDescard(politicsCardsTranslator
					(this.parameters));
		}
		if (this.action instanceof BuildByPermitTile) {
			BuildByPermitTile selectedAction=(BuildByPermitTile) this.action;
			selectedAction.setSelectedCity(cityTranslator
					(this.parameters.remove(0)));
			selectedAction.setSelectedPermitTile(permitTileTranslator
					(this.parameters.remove(0)));
		}
		if (this.action instanceof BuildByKing) {
			BuildByKing selectedAction=(BuildByKing) this.action;
			selectedAction.setSelectedCity(cityTranslator
					(this.parameters.remove(0)));
			selectedAction.setCardsToDescard(politicsCardsTranslator
					(this.parameters));
		}
		if (this.action instanceof ChangePermitTiles) {
			ChangePermitTiles selectedAction=(ChangePermitTiles) this.action;
			selectedAction.setSelectedRegion(regionTranslator
					(this.parameters.remove(0)));
		}
		if (this.action instanceof ElectCouncillorByAssistant) {
			ElectCouncillorByAssistant selectedAction=(ElectCouncillorByAssistant) this.action;
			selectedAction.setNewCouncillor(councillorTranslator
					(this.parameters.remove(0)));
			selectedAction.setCouncilBalcony(councilBalconyTranslator
					(this.parameters.remove(0)));
		}
	}

	private CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		int numberOfRegion=Integer.parseInt(councilBalconyToTranslate);
		CouncilBalcony councilBalconyTranslated=
				this.game.getGameTable().getRegionBoards().get(numberOfRegion).getRegionBalcony();
		return councilBalconyTranslated;
	}

	private Councillor councillorTranslator(String newCouncillorToTranslate) {
		for (Councillor newCouncillorTranslated : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if (newCouncillorTranslated.getColour().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		return null;
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

	private List<PoliticsCard> politicsCardsTranslator(List<String> cardsToTranslate) {
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		Integer numberOfCard;
		for (String card : cardsToTranslate) {
			numberOfCard=Integer.parseInt(card); 
			cardsTranslated.add(this.game.getCurrentPlayer().getHand().get(numberOfCard));
		}
		return cardsTranslated;
	}

	private int numberOfPermitTileTranslator(String numberOfPermitTileToTranslate) {
		int numberOfPermitTileTranslated=Integer.parseInt(numberOfPermitTileToTranslate);
		return numberOfPermitTileTranslated;
	}

	private RegionBoard regionTranslator(String regionToTranslate) {
		int numberOfRegion=Integer.parseInt(regionToTranslate);
		RegionBoard regionTranslated=this.game.getGameTable().getRegionBoards().get(numberOfRegion);
		return regionTranslated;
	}
	
}
