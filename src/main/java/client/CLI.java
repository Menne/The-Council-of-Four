package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import client.view.ClientView;
import client.view.Connection;
import client.view.notifies.ClientGameOverNotify;
import client.view.notifies.ClientViewNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.actionsDTO.QuitDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GameTableDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.marketDTO.MarketableDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.ClientPlayerDTO;
import server.model.gameTable.CouncilBalcony;

public class CLI extends ClientView {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5481163601777854172L;
	private final Scanner scanner;
	private GameDTO clientGame;
	
	public CLI(Connection connection, GameDTO clientGame) {
		super(connection);
		this.scanner=new Scanner(System.in);
		this.clientGame=clientGame;
	}
	
	private boolean parametersNeeded(ActionDTO selectedAction) throws RemoteException {
		return (selectedAction instanceof ActionWithParameters);			
	}
	
	private void insertParametersAndSend(ActionWithParameters actionWithParameters) throws RemoteException {
		this.parametersParser(actionWithParameters);
		if (actionWithParameters.checkIfParametersSetted())
			connection.sendAction(actionWithParameters);
		
	}
	
	public List<String> availableActions() {
		List<String> acceptableStrings=new ArrayList<String>();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			acceptableStrings.add(action.toString().substring(0,2));
		return acceptableStrings;
	}
	
	public ActionDTO actionParser(String selectedAction) {
		for (ActionDTO availableAction : this.clientGame.getAvailableActions())
			if (selectedAction.equals(availableAction.toString().substring(0,2)))
				return availableAction;
		return null;
	}
	
	public ActionDTO parametersParser(ActionWithParameters selectedAction) {
		return selectedAction.setParser(this, this.clientGame).setParameters();
	}
	
	@Override
	public void input() throws RemoteException {
		String input="";
		while (!"quit".equals(input)) {
			try{
				input=this.scanner.nextLine();
			}catch(IllegalStateException e){
				break;
			}
			if (this.availableActions().contains(input)) {
				ActionDTO selectedAction=this.actionParser(input);
				if (parametersNeeded(selectedAction)) {
					ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
					this.insertParametersAndSend(actionWithParameters);
				}
				else
					connection.sendAction(selectedAction);
			}
			else if("quit".equals(input)){
				connection.sendAction(new QuitDTO());
			}
			else
				System.out.println("Sorry, action not available!");	
		}		
	}

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(scanner);
		if(notify instanceof ClientGameOverNotify)
			scanner.close();
	}

	@Override
	public void welcome(String name) throws RemoteException {
		AddPlayerDTO actionDTO=new AddPlayerDTO(name);
		this.connection.sendAction(actionDTO);	
	}


	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void displayAvailableActions(List<ActionDTO> availableActions) {
		System.out.println(availableActions);
	}

	@Override
	public void displayGameTable(GameTableDTO clientGame) {
		System.out.println(clientGame);
	}

	@Override
	public void displayPlayer(ClientPlayerDTO player) {
		System.out.println(player);
	}

	@Override
	public void displayMarket(MarketDTO market) {
		System.out.println(market);
	}
	
	
	@Override
	public RegionDTO askForRegionBoard(List<RegionDTO> acceptableRegions) {
		List<String> acceptableRegionNames=acceptableRegions.stream()
                .map(RegionDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		System.out.println(acceptableRegionNames);
		String regionToTranslate=this.scanner.nextLine();
		while (!acceptableRegionNames.contains(regionToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			regionToTranslate=scanner.nextLine();
		}
		for (RegionDTO region : acceptableRegions)
			if (regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a colour name");
	}

	@Override
	public PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles) {
		List<String> indexes=new ArrayList<>();
		List<String> permitTilesWithIndex=new ArrayList<>();
		int i=1;
		for (PermitTileDTO permitTile : acceptablePermitTiles) {
			indexes.add(""+i);
			permitTilesWithIndex.add(i+": "+permitTile.toString());
			i++;
		}
		System.out.println(permitTilesWithIndex);
		String permitTileToTranslate=scanner.nextLine();
		while (!indexes.contains(permitTileToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			permitTileToTranslate=scanner.nextLine();
		}
		return acceptablePermitTiles.get(Integer.parseInt(permitTileToTranslate)+1);
	}

	@Override
	public CardColourDTO askForCouncillor(List<CardColourDTO> acceptableCouncillors) {
		List<String> acceptableCouncillorsColours=acceptableCouncillors.stream()
                .map(CardColourDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		System.out.println(acceptableCouncillorsColours);
		String newCouncillorToTranslate=this.scanner.nextLine();
		while (!acceptableCouncillorsColours.contains(newCouncillorToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			newCouncillorToTranslate=scanner.nextLine();
		}
		for (CardColourDTO councillor : acceptableCouncillors)
			if (newCouncillorToTranslate.equals(councillor.getName()))
				return councillor;
		throw new IllegalArgumentException("newCouncillorToTranslate is not a colour name");
	}
	
	@Override
	public CardColourDTO[] askForCouncilBalcony(List<CardColourDTO[]> acceptableCounilBalconies) {
		List<String> acceptableCouncilBalconiyNames=Arrays.asList("Sea", "Hill", "Mountain", "King balcony");
		System.out.println(acceptableCounilBalconies.toString());
		String councilBalconyToTranslate=this.scanner.nextLine();
		while (!acceptableCouncilBalconiyNames.contains(councilBalconyToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			councilBalconyToTranslate=scanner.nextLine();
		}
		if (councilBalconyToTranslate.equals("Sea"))
			return acceptableCounilBalconies.get(0);
		if (councilBalconyToTranslate.equals("Hill"))
			return acceptableCounilBalconies.get(1);
		if (councilBalconyToTranslate.equals("Mountain"))
			return acceptableCounilBalconies.get(2);
		if (councilBalconyToTranslate.equals("King council"))
			return acceptableCounilBalconies.get(3);
		throw new IllegalArgumentException("councilBalconyToTranslate is not a valid balcony");
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		List<String> acceptableCityNames=acceptableCities.stream()
                .map(CityDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		System.out.println(acceptableCityNames);
		String cityToTranslate=this.scanner.nextLine();
			while (!acceptableCityNames.toString().contains(cityToTranslate)) {
				System.out.println("Wrong parameter. Try again");
				cityToTranslate=scanner.nextLine();
			}
		for (CityDTO city : acceptableCities)
			if (cityToTranslate.equals(city.getName()))
				return city;
		throw new IllegalArgumentException("cityToTranslate is not a city name");
	}

	//da fare
	@Override
	public List<CardColourDTO> askForPoliticsCards(List<CardColourDTO> acceptablePoliticsCards) {
		List<String> acceptableCardsColours=acceptablePoliticsCards.stream()
                .map(CardColourDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		System.out.println(acceptableCardsColours);
		String cardsToTranslate=scanner.nextLine();
		StringTokenizer st = new StringTokenizer(cardsToTranslate);
		while (!this.checkCards(st, cardsToTranslate, acceptableCardsColours)) {
			cardsToTranslate=scanner.nextLine();
			st = new StringTokenizer(cardsToTranslate);
		}
		List<CardColourDTO> cardsTranslated=new ArrayList<>();
		while (st.hasMoreTokens()) {
			String currentCard=st.nextToken();
			for (CardColourDTO cardTranslated : acceptablePoliticsCards)
				if (cardTranslated.getName().equals(currentCard)) {
					cardsTranslated.add(cardTranslated);
					break;
				}
		}
		return cardsTranslated;
	}
		
	private boolean checkCards(StringTokenizer st, String cardsToTranslate, List<String> acceptableCardsColours) {
		List<String> temporaryAcceptablePoliticsCards=Arrays.asList(cardsToTranslate);
		if (!(st.countTokens()>0 && st.countTokens()<=CouncilBalcony.getNumberofcouncillors())) {
			System.out.println("Remember: you must descard at least 1 card and a maximum of "+ CouncilBalcony.getNumberofcouncillors() +" cards");
			return false;
		}
		while (st.hasMoreTokens()) {
			String currentCard=st.nextToken();
			if (temporaryAcceptablePoliticsCards.contains(currentCard))
				temporaryAcceptablePoliticsCards.remove(currentCard);
			else {
				System.out.println("Wrong cards. Try again");
				return false;
			}
		}
		return true;
	}

	@Override
	public int askForNumberOfPermitTile(List<Integer> acceptableNumberOfPermitTile) {
		System.out.println(acceptableNumberOfPermitTile.toString());
		String numberOfPermitTileToTranslate=this.scanner.nextLine();
		while (!acceptableNumberOfPermitTile.toString().contains(numberOfPermitTileToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			numberOfPermitTileToTranslate=scanner.nextLine();
		}
		return Integer.parseInt(numberOfPermitTileToTranslate);
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		List<String> indexes=new ArrayList<>();
		List<String> offersWithIndex=new ArrayList<>();
		int i=1;
		for (MarketableDTO offeringObject : acceptableObjectsToOffer) {
			indexes.add(""+i);
			offersWithIndex.add(i+": "+offeringObject.toString());
			i++;
		}
		System.out.println(offersWithIndex);
		String offeringObjectToTranslate=scanner.nextLine();
		while (!indexes.contains(offeringObjectToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			offeringObjectToTranslate=scanner.nextLine();
		}
		return acceptableObjectsToOffer.get(Integer.parseInt(offeringObjectToTranslate)+1);
	}
	
	@Override
	public int askForPrice() {
		return Integer.parseInt(this.scanner.nextLine());
	}
	
	@Override
	public boolean askForOtherSelling() {
		if ("yes".equals(this.scanner.nextLine()))
			return true;
		else
			return false;
	}

	@Override
	public OfferDTO askForAcceptingAnOffer(List<OfferDTO> acceptableOffers) {
		List<String> indexes=new ArrayList<>();
		List<String> offersWithIndex=new ArrayList<>();
		int i=1;
		for (OfferDTO offer : acceptableOffers) {
			indexes.add(""+i);
			offersWithIndex.add(i+": "+offer.toString());
			i++;
		}
		System.out.println(offersWithIndex);
		String offerToTranslate=scanner.nextLine();
		while (!indexes.contains(offerToTranslate)) {
			System.out.println("Wrong parameter. Try again");
			offerToTranslate=scanner.nextLine();
		}
		return acceptableOffers.get(Integer.parseInt(offerToTranslate)+1);
	}
	

}
