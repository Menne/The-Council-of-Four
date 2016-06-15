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
	private ClientView view;
	private GameDTO game;
	
	public ElectCouncillorByAssistantParser(ElectCouncillorByAssistantDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}


	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to send an assistant to elect a councillor. Now I need some other infos, like:");
		
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