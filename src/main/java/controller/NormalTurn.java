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


	public <P> void receivePack(P pack) throws IllegalArgumentException{
		if(pack instanceof GiveActionPack){
			GiveActionPack gap= (GiveActionPack) pack;
			switch(gap.getGivenAction()){ 
			case "M1":
				ElectCouncillor m1=new ElectCouncillor(this.gameLogic.getGame());
				this.currentAction=m1;
				this.currentConfiguration=new ElectCouncillorConfiguration(this.gameLogic.getGame(), m1);
				this.gameLogic.notifyObservers(this.currentConfiguration.createAskParameterPack());				
				break;
			case "M2":
				AcquirePermitTile m2=new AcquirePermitTile(this.gameLogic.getGame());
				this.currentAction=m2;
				currentConfiguration=new AcquirePermitTileConfiguration(this.gameLogic.getGame(), m2);
				this.gameLogic.notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "M3":
				BuildByPermitTile m3=new BuildByPermitTile(this.gameLogic.getGame());
				this.currentAction=m3;
				currentConfiguration=new BuildByPermitTileConfiguration(this.gameLogic.getGame(), m3);
				this.gameLogic.notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "M4":
				BuildByKing m4=new BuildByKing(this.gameLogic.getGame());
				this.currentAction=m4;
				currentConfiguration=new BuildByKingConfiguration(this.gameLogic.getGame(), m4);
				this.gameLogic.notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "Q1":
				Action q1 = new EngageAssistant(this.gameLogic.getGame());
				q1.executeAction();
				break;
			case "Q2":
				ChangePermitTiles q2=new ChangePermitTiles(this.gameLogic.getGame());
				this.currentAction=q2;
				currentConfiguration=new ChangePermitTilesConfiguration(this.gameLogic.getGame(), q2);  
				this.gameLogic.notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "Q3":
				ElectCouncillorByAssistant q3=new ElectCouncillorByAssistant(this.gameLogic.getGame());
				this.currentAction=q3;
				currentConfiguration=new ElectCouncillorByAssistantConfiguration(this.gameLogic.getGame(), q3);
				this.gameLogic.notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "Q4":
				Action q4 = new AdditionalMainAction(this.gameLogic.getGame());
				q4.executeAction();
				break;
			case "X":
				this.quickActionAvailable=0;
				break;
			default:
				throw new IllegalArgumentException("Wrong give action pack!");				
			}	
		}
		
		if(pack instanceof GiveParameterPack){
			GiveParameterPack gpp= (GiveParameterPack) pack;
			this.currentConfiguration.setParameters(gpp.getSelectedParameters());
			this.currentAction.executeAction();  
		}		

	}

	@Override
	public void executeTurn() {
		Action action= new PickPoliticsCard(this.gameLogic.getGame());
		action.executeAction();
		while(this.mainActionAvailable>0 || this.quickActionAvailable>0){
			List<String> acceptableActions=new ArrayList<String>();
			if(this.mainActionAvailable!=0 && this.quickActionAvailable!=0)
				acceptableActions=this.acceptableStringForAction("MQ");
			if(this.mainActionAvailable==0 && this.quickActionAvailable!=0)
				acceptableActions=this.acceptableStringForAction("Q");
			if(this.mainActionAvailable==0 && this.quickActionAvailable==0)
				acceptableActions=this.acceptableStringForAction("M");
			AskActionPack aap= new AskActionPack(this.gameLogic.getGame(), acceptableActions);
			this.gameLogic.notifyObservers(aap);
		}
					
		
	}

	

	
	private List<String> acceptableStringForAction(String availableActions){
		List<String> acceptable=new ArrayList<String>();
		if(availableActions.equals("MQ"))
			for(int i=1;i<5;i++){
				acceptable.add("M"+i);
				acceptable.add("Q"+i);
			}
		else
			if(availableActions.equals("M"))
				for(int i=1;i<5;i++)
					acceptable.add("M"+i);
			else
				if(availableActions.equals("Q")){
					for(int i=1;i<5;i++)
						acceptable.add("Q"+i);
					acceptable.add("X");
				}
				else
					throw new IllegalArgumentException("Availble actons can be just"
							+ "M, Q, MQ");
		return acceptable;	
	}

}