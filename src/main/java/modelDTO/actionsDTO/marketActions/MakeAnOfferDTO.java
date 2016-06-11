package modelDTO.actionsDTO.marketActions;

import java.util.ArrayList;
import java.util.List;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.MakeAnOfferParser;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class MakeAnOfferDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7027014433305067100L;
	private List<OfferDTO> offeredObjectsDTO;
	private boolean parametersSetted=false;

	public MakeAnOfferDTO() {
		this.offeredObjectsDTO=new ArrayList<>();
	}
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new MakeAnOfferParser(this, game);
	}


	public List<OfferDTO> getOfferedObjectsDTO() {
		return this.offeredObjectsDTO;
	}

	public void setOfferedObjectsDTO(List<OfferDTO> offeredObjectsDTO) {
		this.offeredObjectsDTO=offeredObjectsDTO;
	}
	
	@Override
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	public void parametersSetted() {
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
