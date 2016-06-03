package client.view.notifies;

import java.util.List;
import java.util.StringTokenizer;

import client.view.socket.CLIsocket;
import modelDTO.parser.ActionParserVisitor;

public class PoliticsCardsNotify implements ClientViewNotify {

	private ActionParserVisitor currentParser;
	private List<String> acceptablePoliticsCards;

	public PoliticsCardsNotify(List<String> acceptablePoliticsCards, ActionParserVisitor currentParser) {
		this.acceptablePoliticsCards=acceptablePoliticsCards;
		this.currentParser=currentParser;
	}

	@Override
	public void stamp(CLIsocket clientView) {
		System.out.println(this.acceptablePoliticsCards);
		this.askCards(clientView);
	}
	
	private void askCards(CLIsocket clientView) {
		String input=clientView.getScanner().nextLine();
		StringTokenizer st = new StringTokenizer(input);
		this.checkNumberOfCards(st, clientView);
	}

	
	private void checkNumberOfCards(StringTokenizer st, CLIsocket clientView) {
		if (!(st.hasMoreTokens() && st.countTokens()<5)) {
			System.out.println("Remember: you must descard at least 1 card and a maximum of 4 cards");
			this.askCards(clientView);
		}
		else 
			this.checkCards(st, clientView);
	}
		
	private void checkCards(StringTokenizer st, CLIsocket clientView) {
		String cards="";
		while (st.hasMoreTokens()) {
			String currentCard=st.nextToken();
			if (!acceptablePoliticsCards.contains(currentCard)) {
				System.out.println("Wrong cards. Retry");
				this.askCards(clientView);
			}
			cards+=" "+currentCard;
		}
		this.currentParser.setCurrentParameter(cards);
	}
		
}
