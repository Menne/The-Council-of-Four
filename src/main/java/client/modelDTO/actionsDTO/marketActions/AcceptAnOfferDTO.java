package client.modelDTO.actionsDTO.marketActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.AcceptAnOfferParser;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.marketDTO.OfferDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This class represents the DTO version of the AcceptAnOffer action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class AcceptAnOfferDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = 4616730650885295726L;
	private OfferDTO offerDTO;
	private boolean parametersSetted=false;

	
	@Override
	public ActionParserVisitor setParser() {
		return new AcceptAnOfferParser(this);
	}

	
	public OfferDTO getOffer() {
		return this.offerDTO;
	}

	public void setOffer(OfferDTO offerDTO) {
		this.offerDTO=offerDTO;
	}
	
	@Override
	public boolean checkIfParametersSet() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSet() {
		this.parametersSetted=true;
	}
	
	
	@Override
	public String toString() {
		return "ao: accept an offer";
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}

}
