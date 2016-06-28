package client.modelDTO.actionsDTO.standardActions;

import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.AcquirePermitTileParser;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class AcquirePermitTileDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8143885166021767590L;
	
	private Integer numberOfPermitTile;
	private RegionDTO chosenRegion;
	private List<PoliticsCardDTO> cardsToDescard;
	private boolean parametersSetted=false;

	public int getNumberOfPermitTiles() {
		return numberOfPermitTile;
	}

	public RegionDTO getChoosenRegion() {
		return chosenRegion;
	}

	public List<PoliticsCardDTO> getCardsToDescard() {
		return cardsToDescard;
	}
	
	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile=numberOfPermitTile;
	}

	public void setCardsToDescard(List<PoliticsCardDTO> cardsToDescard) {
		this.cardsToDescard=cardsToDescard;
	}

	public void setChosenRegion(RegionDTO chosenRegion) {
		this.chosenRegion=chosenRegion;
	}
	
	@Override
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}
	
	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new AcquirePermitTileParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
