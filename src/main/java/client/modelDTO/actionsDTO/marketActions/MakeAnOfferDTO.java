package client.modelDTO.actionsDTO.marketActions;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.MakeAnOfferParser;
import client.modelDTO.marketDTO.OfferDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This class represents the DTO version of the MakeAnOffer action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class MakeAnOfferDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = 7027014433305067100L;
	private List<OfferDTO> offeredObjectsDTO;
	private boolean parametersSetted=false;

	public MakeAnOfferDTO() {
		this.offeredObjectsDTO=new ArrayList<>();
	}
	
	@Override
	public ActionParserVisitor setParser() {
		return new MakeAnOfferParser(this);
	}


	public List<OfferDTO> getOfferedObjectsDTO() {
		return this.offeredObjectsDTO;
	}

	public void setOfferedObjectsDTO(List<OfferDTO> offeredObjectsDTO) {
		this.offeredObjectsDTO=offeredObjectsDTO;
	}
	
	@Override
	public boolean checkIfParametersSet() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSet() {
		this.parametersSetted=true;
	}
	
	public void addOfferToList(OfferDTO offerDTO) {
		this.offeredObjectsDTO.add(offerDTO);
	}

	@Override
	public String toString() {
		return "mo: make an offer";
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}

	
}
