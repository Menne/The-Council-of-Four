package modelDTO.parser;

import java.util.ArrayList;
import java.util.List;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.RegionDTO;

public class ElectCouncillorParser implements ActionParserVisitor {

	private ElectCouncillorDTO selectedAction;
	private ClientView view;
	private GameDTO game;
	
	public ElectCouncillorParser(ElectCouncillorDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}
	
	
	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to elect a councillor. Now I need some other infos, like:");
		
		this.view.displayMessage("the colour of the councillor you want to elect");
		this.selectedAction.setNewCouncillor(this.view.askForCouncillor
				(this.game.getClientGameTable().getClientCouncillorReserve()));
		
		this.view.displayMessage("the name of the region in which you want to change the councillor");
		List<CardColourDTO[]> acceptableCouncilBalconies=new ArrayList<>();
		for (RegionDTO region : this.game.getClientGameTable().getClientRegions())
			acceptableCouncilBalconies.add(region.getBalcony());
		acceptableCouncilBalconies.add(this.game.getClientGameTable().getClientKingBalcony());
		this.selectedAction.setCouncilBalcony(this.view.askForCouncilBalcony
				(acceptableCouncilBalconies));
		
		this.selectedAction.parametersSetted();
		
		return this.selectedAction;
	}

}
