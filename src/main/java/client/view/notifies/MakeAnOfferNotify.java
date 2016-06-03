package client.view.notifies;

import java.util.List;

import client.view.socket.CLIsocket;
import modelDTO.parser.ActionParserVisitor;

public class MakeAnOfferNotify implements ClientViewNotify {

	private List<String> parameters;
	private ActionParserVisitor currentParser;

	public MakeAnOfferNotify(List<String> parameters, ActionParserVisitor currentParser) {
		this.parameters=parameters;
		this.currentParser=currentParser;
	}
	
	
	@Override
	public void stamp(CLIsocket clientView) {
		System.out.println(parameters);
		String input=clientView.getScanner().nextLine();
		while (!parameters.contains(input)) {
			System.out.println("Wrong parameter. Retry");
			input=clientView.getScanner().nextLine();
		}
		this.currentParser.setCurrentParameter(input);
		System.out.println("What's the price of the element you selected?");
		input=clientView.getScanner().nextLine();
		this.currentParser.setCurrentParameter(input);
				
	} 
		
	

}
