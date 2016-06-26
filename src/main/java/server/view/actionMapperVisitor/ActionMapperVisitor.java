package server.view.actionMapperVisitor;

import client.modelDTO.actionsDTO.PickPermitTileActionDTO;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import server.model.actions.bonusActions.ChooseCityBonusAction;
import server.model.actions.bonusActions.PickPermitTileBonusAction;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.actions.standardActions.ElectCouncillorByAssistant;

public interface ActionMapperVisitor {

	public AcquirePermitTile map(AcquirePermitTileDTO selectedActionDTO);
	public BuildByKing map(BuildByKingDTO selectedActionDTO);
	public BuildByPermitTile map(BuildByPermitTileDTO selectedActionDTO);
	public ChangePermitTiles map(ChangePermitTilesDTO selectedActionDTO);
	public ElectCouncillor map(ElectCouncillorDTO selectedActionDTO);
	public ElectCouncillorByAssistant map(ElectCouncillorByAssistantDTO selectedActionDTO);
	public MakeAnOffer map(MakeAnOfferDTO selectedActionDTO);
	public AcceptAnOffer map(AcceptAnOfferDTO selectedActionDTO);
	public ChooseCityBonusAction map(ChooseCityActionDTO selectedActionDTO);
	public PurchasedPermitTileAction map(PurchasedPermitTileActionDTO selectedActionDTO);
	public PickPermitTileBonusAction map(PickPermitTileActionDTO pickPermitTileActionDTO);
	
}
