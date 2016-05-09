package controller;

import java.util.ArrayList;
import java.util.List;

import actions.*;
import actionsConfiguration.AcquirePermitTileConfiguration;
import actionsConfiguration.ActionConfiguration;
import actionsConfiguration.BuildByKingConfiguration;
import actionsConfiguration.BuildByPermitTileConfiguration;
import actionsConfiguration.ChangePermitTilesConfiguration;
import actionsConfiguration.ElectCouncillorByAssistantConfiguration;
import actionsConfiguration.ElectCouncillorConfiguration;
import view.GiveActionPack;
import view.GiveParameterPack;

/**
 * It's the class that manage the entire turn of a player.
 * Everything start from executeAction method.
 * Initially the player has one main action and one quick action available
 * @author Luca
 *
 */

public class NormalTurn extends Turn{
	
	private int mainActionAvailable;
	private int quickActionAvailable;
	private NeedParameters currentAction;
	private ActionConfiguration currentConfiguration;
	
	public NormalTurn(GameLogic gameLogic){
		super(gameLogic);
		this.mainActionAvailable=1;
		this.quickActionAvailable=1;
	}
	
	

	public int getMainActionAvailable() {
		return mainActionAvailable;
	}

	public void incrementMainActionAvailable() {
		this.mainActionAvailable++;
	}
	
	public void decrementMainActionAvailable() {
		this.mainActionAvailable--;
	}



	public int getQuickActionAvailable() {
		return quickActionAvailable;
	}



	public void decrementQuickActionAvailable() {
		this.quickActionAvailable--;
	}

	/**
	 * Is called from the update method of the GameLogic when the view notify a pack to it .
	 * If the pack is a GiveActionPack this method instances the right action 
	 * and asks the parameters to the view (just if the action need them)
	 * If the pack is a GiveParametersPack this method sets the parameter of the action
	 * and execute the action.
	 * If the action execution return false we will restart from the ask action phase.
	 * @param pack is the pack received from the GameLogic 
	 * @throws IllegalArgumentException
	 */
	public <P> void receivePack(P pack) throws IllegalArgumentException{
		boolean actionResult;
		if(pack instanceof GiveActionPack){
			GiveActionPack gap= (GiveActionPack) pack;
			switch(gap.getGivenAction()){ 
			case "m1":
				ElectCouncillor m1=new ElectCouncillor(this.gameLogic.getGame());
				this.currentAction=m1;
				this.currentConfiguration=new ElectCouncillorConfiguration(this.gameLogic.getGame(), m1);
				this.askForParameters();				
				break;
			case "m2":
				AcquirePermitTile m2=new AcquirePermitTile(this.gameLogic.getGame());
				this.currentAction=m2;
				currentConfiguration=new AcquirePermitTileConfiguration(this.gameLogic.getGame(), m2);
				this.askForParameters();
				break;
			case "m3":
				BuildByPermitTile m3=new BuildByPermitTile(this.gameLogic.getGame());
				this.currentAction=m3;
				currentConfiguration=new BuildByPermitTileConfiguration(this.gameLogic.getGame(), m3);
				this.askForParameters();
				break;
			case "m4":
				BuildByKing m4=new BuildByKing(this.gameLogic.getGame());
				this.currentAction=m4;
				currentConfiguration=new BuildByKingConfiguration(this.gameLogic.getGame(), m4);
				this.askForParameters();
				break;
			case "q1":
				Action q1 = new EngageAssistant(this.gameLogic.getGame());
				actionResult=q1.executeAction();
				if(!actionResult){
					this.gameLogic.notifyObservers(new ErrorSignal());
					this.askForAction();
				}
				break;
			case "q2":
				ChangePermitTiles q2=new ChangePermitTiles(this.gameLogic.getGame());
				this.currentAction=q2;
				currentConfiguration=new ChangePermitTilesConfiguration(this.gameLogic.getGame(), q2);  
				this.askForParameters();
				break;
			case "q3":
				ElectCouncillorByAssistant q3=new ElectCouncillorByAssistant(this.gameLogic.getGame());
				this.currentAction=q3;
				currentConfiguration=new ElectCouncillorByAssistantConfiguration(this.gameLogic.getGame(), q3);
				this.askForParameters();
				break;
			case "q4":
				Action q4 = new AdditionalMainAction(this.gameLogic.getGame());
				actionResult=q4.executeAction();
				if(!actionResult){
					this.gameLogic.notifyObservers(new ErrorSignal());
					this.askForAction();
				}
				break;
			case "ex":
				this.quickActionAvailable=0;
				break;
			default:
				throw new IllegalArgumentException("Wrong give action pack!");				
			}	
		}
		
		if(pack instanceof GiveParameterPack){
			GiveParameterPack gpp= (GiveParameterPack) pack;
			this.currentConfiguration.setParameters(gpp.getSelectedParameters());
			actionResult=this.currentAction.executeAction();
			if(!actionResult){
				this.gameLogic.notifyObservers(new ErrorSignal());
				this.askForAction();
			}
		}		

	}
	
	/**
	 * Is the entry point used from the GameLogic
	 */
	@Override
	public void executeTurn() {
		Action action= new PickPoliticsCard(this.gameLogic.getGame());
		action.executeAction();
		while(this.mainActionAvailable>0 || this.quickActionAvailable>0){
			this.askForAction();
		}
				
	}

	/**
	 * Represents the ask parameter phase. Passes the AskParameterPack to the view.
	 * If it has an empty field we will restart from the ask action phase.
	 */
	private void askForParameters(){
		AskParameterPack app=this.currentConfiguration.createAskParameterPack();
		if(this.isAcceptableParametersEmpty(app.getAcceptableParameters())){
			this.gameLogic.notifyObservers(new ErrorSignal());
			this.askForAction();
		}
		else
			this.gameLogic.notifyObservers(app);
	}
	
	/**
	 * Represent the ask action phase. Passes the AskActonPack to the view.
	 */
	private void askForAction(){
		List<String> acceptableActions=new ArrayList<String>();
		acceptableActions=this.acceptableStringForAction();
		AskActionPack aap= new AskActionPack(this.gameLogic.getGame(), acceptableActions);
		this.gameLogic.notifyObservers(aap);
	}
	

	private List<String> acceptableStringForAction() {
		List<String> acceptable=new ArrayList<String>();
		if (this.mainActionAvailable>0 && this.quickActionAvailable>0) {
			acceptable.add("m1: elect a councillor");
			acceptable.add("m2: acquire a permit tile");
			acceptable.add("m3: build an emporium using a permit tile");
			acceptable.add("m4: build an emporium with the help of the king");
			acceptable.add("q1: engage an assistant");
			acceptable.add("q2: change permit tiles");
			acceptable.add("q3: send an assistant to elect a councillor");
			acceptable.add("q4: make an additional main action");
		}
		else 
			if (this.mainActionAvailable>0) {
				acceptable.add("m1: elect a councillor");
				acceptable.add("m2: acquire a permit tile");
				acceptable.add("m3: build an emporium using a permit tile");
				acceptable.add("m4: build an emporium with the help of the king");
			}
			else 
				if (quickActionAvailable>0) {
					acceptable.add("q1: engage an assistant");
					acceptable.add("q2: change permit tiles");
					acceptable.add("q3: send an assistant to elect a councillor");
					acceptable.add("q4: make an additional main action");
					acceptable.add("ex: exit turn");
		}
		else
			throw new IllegalArgumentException("Availble actons can be just"
					+ "m, q, mq");
	return acceptable;	
	}
	
	private boolean isAcceptableParametersEmpty(List<List<String>> acceptableParameters){
		for(List<String> parameterList : acceptableParameters)
			if(parameterList.isEmpty())
				return true;
		return false;
	}

}