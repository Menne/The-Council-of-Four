package view;

import model.actions.AdditionalMainActionBonus;

public class BonusNotify implements ViewNotify {
	
	private final AdditionalMainActionBonus action;
	
	public BonusNotify(AdditionalMainActionBonus action){
		this.action=action;
	}
	
	@Override
	public void stamp(View view) {
		System.out.println("You have got an additonal Main Action Bonus!");
		view.notifyObserver(action);
	}

}
