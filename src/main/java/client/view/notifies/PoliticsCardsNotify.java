package client.view.notifies;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import client.view.socket.CLI;
import modelDTO.parser.ActionParserVisitor;
import server.model.gameTable.CouncilBalcony;

public class PoliticsCardsNotify implements ClientViewNotify {

	private ActionParserVisitor currentParser;
	private List<String> acceptablePoliticsCards;

	public PoliticsCardsNotify(List<String> acceptablePoliticsCards, ActionParserVisitor currentParser) {
		this.acceptablePoliticsCards=acceptablePoliticsCards;
		this.currentParser=currentParser;
	}

	@Override
	public void stamp(CLI clientView) {
		System.out.println(this.acceptablePoliticsCards);
		String input=clientView.getScanner().nextLine();
		StringTokenizer st = new StringTokenizer(input);
		while (!this.checkCards(st)) {
			input=clientView.getScanner().nextLine();
			st = new StringTokenizer(input);
		}
		this.currentParser.setCurrentParameter(input);
	}
		
	private boolean checkCards(StringTokenizer st) {
		List<String> temporaryAcceptablePoliticsCards=new ArrayList<>(this.acceptablePoliticsCards);
		if (!(st.countTokens()>0 && st.countTokens()<=CouncilBalcony.getNumberofcouncillors())) {
			System.out.println("Remember: you must descard at least 1 card and a maximum of "+ CouncilBalcony.getNumberofcouncillors() +" cards");
			return false;
		}
		while (st.hasMoreTokens()) {
			String currentCard=st.nextToken();
			if (temporaryAcceptablePoliticsCards.contains(currentCard)) {
				temporaryAcceptablePoliticsCards.remove(currentCard);
			}
			else {
				System.out.println("Wrong cards. Retry");
				return false;
			}
		}
		return true;
	}
	
}
