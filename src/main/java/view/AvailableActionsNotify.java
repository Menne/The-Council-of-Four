package view;

import java.util.List;

import model.actions.Action;

public class AvailableActionsNotify implements ViewNotify {

	private final List<Action> availableActions;
	
	public AvailableActionsNotify(List<Action> availableActions){
		this.availableActions=availableActions;
	}
	
	@Override
	public void stamp() {
		System.out.println("Your available actions are the following, choose one of them.\n");
		for(Action action : this.availableActions)
			System.out.println(action+"\n");
	}

}
