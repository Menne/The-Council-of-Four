package server.model.mappers;

import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
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

/**
 * This interface provides all the methods required to map an action DTO into its corresponding real object
 * @author cg31
 *
 */
public interface ActionMapperVisitor {

	/**
	 * This method creates a new AcquirePermitTile action according to the AcquirePermitTileDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * return an AcquirePermitTile action with all the parameters set
	 */
	public AcquirePermitTile map(AcquirePermitTileDTO selectedActionDTO);
	
	/**
	 * This method creates a new BuildByKing action according to the BuildByKingDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * return a BuildByKing action with all the parameters set
	 */
	public BuildByKing map(BuildByKingDTO selectedActionDTO);
	
	/**
	 * This method creates a new BuildByPermitTile action according to the BuildByPermitTileDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an BuildByPermitTile action with all the parameters set
	 */
	public BuildByPermitTile map(BuildByPermitTileDTO selectedActionDTO);
	
	/**
	 * This method creates a new ChangePermitTiles action according to the ChangePermitTilesDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ChangePermitTiles action with all the parameters set
	 */
	public ChangePermitTiles map(ChangePermitTilesDTO selectedActionDTO);
	
	/**
	 * This method creates a new ElectCouncillor action according to the ElectCouncillorDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ElectCouncillor action with all the parameters set
	 */
	public ElectCouncillor map(ElectCouncillorDTO selectedActionDTO);
	
	/**
	 * This method creates a new ElectCouncillorByAssistant action according to the ElectCouncillorByAssistantDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ElectCouncillorByAssistant action with all the parameters set
	 */
	public ElectCouncillorByAssistant map(ElectCouncillorByAssistantDTO selectedActionDTO);
	
	/**
	 * This method creates a new MakeAnOffer action according to the MakeAnOfferDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an MakeAnOffer action with all the parameters set
	 */
	public MakeAnOffer map(MakeAnOfferDTO selectedActionDTO);
	
	/**
	 * This method creates a new AcceptAnOffer action according to the AcceptAnOfferDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an AcceptAnOffer action with all the parameters set
	 */
	public AcceptAnOffer map(AcceptAnOfferDTO selectedActionDTO);
	
	/**
	 * This method creates a new ChooseCityBonusAction action according to the ChooseCityBonusActionDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ChooseCityBonusAction action with all the parameters set
	 */
	public ChooseCityBonusAction map(ChooseCityActionDTO selectedActionDTO);
	
	/**
	 * This method creates a new PurchasedPermitTileAction action according to the PurchasedPermitTileActionDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an PurchasedPermitTileAction action with all the parameters set
	 */
	public PurchasedPermitTileAction map(PurchasedPermitTileActionDTO selectedActionDTO);
	
	/**
	 * This method creates a new PickPermitTileBonusAction action according to the PickPermitTileBonusActionDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an PickPermitTileBonusAction action with all the parameters set
	 */
	public PickPermitTileBonusAction map(PickPermitTileActionDTO pickPermitTileActionDTO);
	
}
