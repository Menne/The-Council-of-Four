package view;

import java.util.List;

public class GiveParameterPack {

	private final List<String> selectedParameters;
	
	public GiveParameterPack(List<String> selectedParameters){
		this.selectedParameters=selectedParameters;
	}

	public List<String> getSelectedParameters() {
		return selectedParameters;
	}
	
}
