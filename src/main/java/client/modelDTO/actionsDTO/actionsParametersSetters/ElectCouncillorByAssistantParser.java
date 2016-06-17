package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

public class ElectCouncillorByAssistantParser implements ActionParserVisitor {

	private ElectCouncillorByAssistantDTO selectedAction;
	
	public ElectCouncillorByAssistantParser(ElectCouncillorByAssistantDTO selectedAction) {
		this.selectedAction=selectedAction;

	}


	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to send an assistant to elect a councillor. Now I need some other infos, like:");
		
		view.displayMessage("the colour of the councillor you want to elect");
		this.selectedAction.setNewCouncillor(view.askForCouncillor
				(game.getClientGameTable().getClientCouncillorReserve()));
		
		view.displayMessage("the name of the region in which you want to change the councillor");
		List<CardColourDTO[]> acceptableCouncilBalconies=new ArrayList<>();
		for (RegionDTO region : game.getClientGameTable().getClientRegions())
			acceptableCouncilBalconies.add(region.getBalcony());
		acceptableCouncilBalconies.add(game.getClientGameTable().getClientKingBalcony());
		this.selectedAction.setCouncilBalcony(view.askForCouncilBalcony
				(acceptableCouncilBalconies));
		
		this.selectedAction.parametersSetted();
		
		return this.selectedAction;
	}

}
