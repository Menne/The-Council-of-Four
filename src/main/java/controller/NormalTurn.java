package controller;

import java.util.ArrayList;
import java.util.List;

import actions.*;
import model.Game;
import observerPattern.Observable;
import view.GiveActionPack;
import view.View;

public class NormalTurn extends Turn{
	
	private int mainActionAvailable;
	private int quickActionAvailable;
	private NeedParameters currentAction;
	
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
			GiveActionPack gap= (GiveActionPack) change;
			switch(gap.getGivenAction()){ 
			case "M1":
				this.currentAction=new ElectCouncillor(this.game);
				notifyObservers(this.currentAction.createAskParameterPack());				
				break;
			case "M2":
				this.currentAction=new AcquirePermitTile(this.game);
				notifyObservers(this.currentAction.createAskParameterPack());
				break;
			case "M3":
				this.currentAction=new BuildByPermitTile(this.game);
				notifyObservers(this.currentAction.createAskParameterPack());
				break;
			case "M4":
				this.currentAction=new BuildByKing(this.game);
				notifyObservers(this.currentAction.createAskParameterPack());
				break;
			case "Q1":
				Action q1 = new EngageAssistant(this.game);
				q1.executeAction();
				break;
			case "Q2":
				this.currentAction=new ChangePermitTiles(this.game);
				notifyObservers(this.currentAction.createAskParameterPack());
				break;
			case "Q3":
				this.currentAction=new ElectCouncillorByAssistant(this.game);
				notifyObservers(this.currentAction.createAskParameterPack());
				break;
			case "Q4":
				Action q4 = new AdditionalMainAction(this.game);
				q4.executeAction();
				break;
			default:
				throw new IllegalArgumentException("Wrong give action pack!");				
			}	
		}		
/*		if(change instanceof GiveParameterPack){
			GiveParameterPack gpp= (GiveParameterPack) change;
			ActionExecutor actionExecutor=
					new ActionExecutor(game, this.currentAction, gpp.getSelectedParameters());
			actionExecutor.callAction();			
		}	*/ 	
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