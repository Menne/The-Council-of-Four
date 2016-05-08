package controller;

import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.PickPoliticsCard;
import gameStuff.RegionBoard;
import model.Game;
import observerPattern.Observable;
import view.GiveActionPack;
import view.GiveParameterPack;
import view.View;

public class NormalTurn extends Turn {
	
	private int mainActionAvailable;
	private int quickActionAvailable;
	private String currentAction;
	
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
	public <C> void update(Observable o, C change) {
		if(change instanceof GiveActionPack){
			GiveActionPack gap= (GiveActionPack) change;
			this.currentAction=gap.getGivenAction();
			if(gap.getGivenAction().equals("M1")){
				//preparo ask parameter pack
				List<String> parametersName=new ArrayList<String>();
				parametersName.add("Region where you want to acquire");
				parametersName.add("Permit tile you want to acquire");
				parametersName.add("Cards of your hand you want to use to satisfy the council");
				
				List<List<String>> acceptableStrings= new ArrayList<List<String>>();
				List<String> regionNames=new ArrayList<String>();
				for(RegionBoard region : this.game.getGameTable().getRegionBoards())
					regionNames.add(region.getName());
				acceptableStrings.add(regionNames);
				
				List<String> permitTileNumbers=new ArrayList<String>();
				permitTileNumbers.add("0");
				permitTileNumbers.add("1");
				acceptableStrings.add(permitTileNumbers);
				
				List<String> cardsNumbers=new ArrayList<String>();
				int maxNumberOfCards=this.game.getCurrentPlayer().getHand().size();
				for(Integer i=0; i<=maxNumberOfCards; i++)
					cardsNumbers.add(i.toString());
				acceptableStrings.add(cardsNumbers);
								
				AskParameterPack app=new AskParameterPack(parametersName, acceptableStrings);
				
				//invio ask parameter pack
				notifyObservers(app);
			}
			
			if(gap.getGivenAction().equals("M2")){
				
			}		
		}
		
		if(change instanceof GiveParameterPack){
			GiveParameterPack gpp= (GiveParameterPack) change;
			ParametersSetter ae=new ParametersSetter(game, this.currentAction, gpp.getSelectedParameters());
			ae.callAction();
			
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