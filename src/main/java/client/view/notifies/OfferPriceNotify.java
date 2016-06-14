package client.view.notifies;

import java.util.List;
import java.util.Scanner;

import client.view.ClientView;
import modelDTO.parser.ActionParserVisitor;

public class OfferPriceNotify implements ClientViewNotify {
	
	private final List<String> parameters;
	private ActionParserVisitor currentParser;
	
	public OfferPriceNotify(List<String> parameters, ActionParserVisitor currentParser) {
		this.parameters=parameters;
		this.currentParser=currentParser;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println("What's the price of the element you selected?");
		String input=scanner.nextLine();
			while (!parameters.contains(input)) {
				System.out.println("I didn't understand");
				input=scanner.nextLine();
			}
		this.currentParser.setCurrentParameter(input);
	}

	@Override
	public void updateView(ClientView view) {
		// TODO Auto-generated method stub
		
	}

}