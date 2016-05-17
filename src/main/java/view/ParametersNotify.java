package view;

import java.util.List;

import model.parser.ActionParserVisitor;

public class ParametersNotify implements ViewNotify {
	
	private final List<String> parameters;
	private ActionParserVisitor currentParser;
	
	public ParametersNotify(List<String> parameters, ActionParserVisitor currentParser) {
		this.parameters=parameters;
		this.currentParser=currentParser;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println(parameters);
		String input=view.getScanner().nextLine();
			while (!parameters.contains(input)) {
				System.out.println("Wrong parameter. Retry");
				input=view.getScanner().nextLine();
			}
		this.currentParser.setCurrentParameter(input);
	}

}
