package client.modelDTO.actionsDTO.standardActions;

import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsParametersSetters.AcquirePermitTileParametersSetter;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.actions.Action;
import server.model.mappers.ActionMapperVisitor;

/**
 * This class represents the DTO version of the AcquirePermitTile action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class AcquirePermitTileDTO implements ActionDTO, ActionWithParameters {

	private static final long serialVersionUID = 8143885166021767590L;
	private Integer numberOfPermitTile;
	private RegionDTO chosenRegion;
	private List<PoliticsCardDTO> cardsToDescard;
	private boolean parametersSetted=false;

	public int getNumberOfPermitTiles() {
		return this.numberOfPermitTile;
	}

	public RegionDTO getChoosenRegion() {
		return this.chosenRegion;
	}

	public List<PoliticsCardDTO> getCardsToDescard() {
		return this.cardsToDescard;
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
	public boolean checkIfParametersSet() {
		return parametersSetted;
	}
	
	@Override
	public void parametersSet() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}

	@Override
	public ActionParametersSetter setParser() {
		return new AcquirePermitTileParametersSetter(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
