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
import model.Game;
import observerPattern.Observable;
import view.GiveActionPack;
import view.GiveParameterPack;
import view.View;

public class NormalTurn extends Turn{
	
	private int mainActionAvailable;
	private int quickActionAvailable;
	private NeedParameters currentAction;
	private ActionConfiguration currentConfiguration;
	
	public NormalTurn(View view, Game game){
		super(view,game);
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



	@Override
	public <C> void update(Observable o, C change) throws IllegalArgumentException{
		if(change instanceof GiveActionPack){
		//	ActionConfiguration currentConfiguration;
			GiveActionPack gap= (GiveActionPack) change;
			switch(gap.getGivenAction()){ 
			case "M1":
				ElectCouncillor m1=new ElectCouncillor(this.game);
				this.currentAction=m1;
				this.currentConfiguration=new ElectCouncillorConfiguration(this.game, m1);
				notifyObservers(this.currentConfiguration.createAskParameterPack());				
				break;
			case "M2":
				AcquirePermitTile m2=new AcquirePermitTile(this.game);
				this.currentAction=m2;
				currentConfiguration=new AcquirePermitTileConfiguration(game, m2);
				notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "M3":
				BuildByPermitTile m3=new BuildByPermitTile(this.game);
				this.currentAction=m3;
				currentConfiguration=new BuildByPermitTileConfiguration(this.game, m3);
				notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "M4":
				BuildByKing m4=new BuildByKing(this.game);
				this.currentAction=m4;
				currentConfiguration=new BuildByKingConfiguration(this.game, m4);
				notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "Q1":
				Action q1 = new EngageAssistant(this.game);
				q1.executeAction();
				break;
			case "Q2":
				ChangePermitTiles q2=new ChangePermitTiles(this.game);
				this.currentAction=q2;
				currentConfiguration=new ChangePermitTilesConfiguration(this.game, q2);  
				notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "Q3":
				ElectCouncillorByAssistant q3=new ElectCouncillorByAssistant(this.game);
				this.currentAction=q3;
				currentConfiguration=new ElectCouncillorByAssistantConfiguration(this.game, q3);
				notifyObservers(currentConfiguration.createAskParameterPack());
				break;
			case "Q4":
				Action q4 = new AdditionalMainAction(this.game);
				q4.executeAction();
				break;
			default:
				throw new IllegalArgumentException("Wrong give action pack!");				
			}	
		}
		
		if(change instanceof GiveParameterPack){
			GiveParameterPack gpp= (GiveParameterPack) change;
			this.currentConfiguration.setParameters(gpp.getSelectedParameters());
			this.currentAction.executeAction();  
		}		

	}

	@Override
	public void executeTurn() {
		Action action= new PickPoliticsCard(game);
		action.executeAction();
		while(this.mainActionAvailable>0 || this.quickActionAvailable>0){
			List<String> acceptableActions=new ArrayList<String>();
			if(this.mainActionAvailable!=0 && this.quickActionAvailable!=0)
				acceptableActions=this.acceptableStringForAction("MQ");
			if(this.mainActionAvailable==0 && this.quickActionAvailable!=0)
				acceptableActions=this.acceptableStringForAction("Q");
			if(this.mainActionAvailable==0 && this.quickActionAvailable==0)
				acceptableActions=this.acceptableStringForAction("M");
			AskActionPack aap= new AskActionPack(game, acceptableActions);
			notifyObservers(aap);
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
				if(availableActions.equals("Q"))
					for(int i=1;i<5;i++)
						acceptable.add("Q"+i);
				else
					throw new IllegalArgumentException("Availble actons can be just"
							+ "M, Q, MQ");
		return acceptable;	
	}

}