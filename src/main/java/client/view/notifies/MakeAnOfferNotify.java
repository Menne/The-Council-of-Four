package client.view.notifies;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import client.view.ClientView;
import modelDTO.parser.ActionParserVisitor;

public class MakeAnOfferNotify implements ClientViewNotify {

	private List<String> parameters;
	private ActionParserVisitor currentParser;

	public MakeAnOfferNotify(List<String> parameters, ActionParserVisitor currentParser) {
		this.parameters=parameters;
		this.currentParser=currentParser;
	}
	
	
	@Override
	public void stamp(Scanner scanner) {
		List<String> indexes=new ArrayList<>();
		List<String> parametersWithIndex=new ArrayList<>();
		int i=1;
		for (String parameter : this.parameters) {
			indexes.add(""+i);
			parametersWithIndex.add(i+": "+parameter);
			i++;
		}
		System.out.println(parametersWithIndex);
		String input=scanner.nextLine();
		while (!indexes.contains(input)) {
			System.out.println("Wrong parameter. Try again");
			input=scanner.nextLine();
		}
		this.currentParser.setCurrentParameter(input);		
	}


	@Override
	public void updateView(ClientView view) {
		// TODO Auto-generated method stub
		
	}

}
