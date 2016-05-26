package client.view.notifies;

import java.util.List;

import client.view.CLI;
import modelDTO.parser.ActionParserVisitor;

public class ParametersNotify implements ClientViewNotify {
	
	private final List<String> parameters;
	private ActionParserVisitor currentParser;
	
	public ParametersNotify(List<String> parameters, ActionParserVisitor currentParser) {
		this.parameters=parameters;
		this.currentParser=currentParser;
	}

	@Override
	public void stamp(CLI clientView) {
		System.out.println(parameters);
		String input=clientView.getScanner().nextLine();
			while (!parameters.contains(input)) {
				System.out.println("Wrong parameter. Retry");
				input=clientView.getScanner().nextLine();
			}
		this.currentParser.setCurrentParameter(input);
	}

}