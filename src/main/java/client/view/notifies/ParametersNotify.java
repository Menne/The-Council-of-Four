package client.view.notifies;

import java.util.List;
import java.util.Scanner;

import modelDTO.parser.ActionParserVisitor;

public class ParametersNotify implements ClientViewNotify {
	
	private final List<String> parameters;
	private ActionParserVisitor currentParser;
	
	public ParametersNotify(List<String> parameters, ActionParserVisitor currentParser) {
		this.parameters=parameters;
		this.currentParser=currentParser;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println(parameters);
		String input=scanner.nextLine();
			while (!parameters.contains(input)) {
				System.out.println("Wrong parameter. Retry");
				input=scanner.nextLine();
			}
		this.currentParser.setCurrentParameter(input);
	}

}