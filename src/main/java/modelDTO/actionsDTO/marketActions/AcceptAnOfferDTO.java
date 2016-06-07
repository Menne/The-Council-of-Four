package modelDTO.actionsDTO.marketActions;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.parser.AcceptAnOfferParser;
import modelDTO.parser.ActionParserVisitor;
import server.model.actions.Action;
import server.view.mapperVisitor.ActionDTOMapper;

public class AcceptAnOfferDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4616730650885295726L;
	private OfferDTO offerDTO;
	private boolean parametersSetted=false;

	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new AcceptAnOfferParser(this, game);
	}

	
	public OfferDTO getOffer() {
		return offerDTO;
	}

	public void setOffer(OfferDTO offerDTO) {
		this.offerDTO=offerDTO;
	}
	
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}
	
	
	@Override
	public String toString() {
		return "ao: accept an offer";
	}

	@Override
	public Action startVisitor(ActionDTOMapper mapper) {
		return mapper.map(this);
	}

}
