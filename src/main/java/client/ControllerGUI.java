package client;

import java.rmi.RemoteException;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.MoveToNextDTO;
import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.AddictionalMainActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.actionsDTO.standardActions.EngageAssistantDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.GUI;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllerGUI {
	
	private GameDTO clientGame;
	private GUI view;
	
	
	public void setClientGame(GameDTO clientGame) {
		this.clientGame=clientGame;
	}
	
	public void setView(GUI view) {
		this.view=view;
	}


	@FXML
	private Pane seaRegion;
	
	@FXML
	private Pane hillRegion;
	  
	@FXML
	private Pane mountainRegion;
	
	@FXML
	private Pane kingBalcony;
	
	@FXML
	private Pane seaBalcony;
	
	@FXML
	private Pane hillBalcony;
	
	@FXML
	private Pane mountainBalcony;
	
	@FXML
	private ImageView seaConcillor1;
	
	@FXML
	private ImageView seaConcillor2;
	
	@FXML
	private ImageView seaConcillor3;
	
	@FXML
	private ImageView seaConcillor4;
	
	@FXML
	private ImageView hillConcillor1;
	
	@FXML
	private ImageView hillConcillor2;
	
	@FXML
	private ImageView hillConcillor3;
	
	@FXML
	private ImageView hillConcillor4;
	
	@FXML
	private ImageView mountainConcillor1;
	
	@FXML
	private ImageView mountainConcillor2;
	
	@FXML
	private ImageView mountainConcillor3;
	
	@FXML
	private ImageView mountainConcillor4;
	
	@FXML
	private ImageView kingConcillor1;
	
	@FXML
	private ImageView kingConcillor2;
	
	@FXML
	private ImageView kingConcillor3;
	
	@FXML
	private ImageView kingConcillor4;
	
	@FXML
	private ImageView reserveConcillor1;
	
	@FXML
	private ImageView reserveConcillor2;
	
	@FXML
	private ImageView reserveConcillor3;
	
	@FXML
	private ImageView reserveConcillor4;
	
	@FXML
	private ImageView reserveConcillor5;
	
	@FXML
	private ImageView reserveConcillor6;
	
	@FXML
	private ImageView reserveConcillor7;
	
	@FXML
	private ImageView reserveConcillor8;
	
	@FXML
	private Button m1; 
	
	@FXML
	private Button m2;
	
	@FXML
	private Button m3;
	
	@FXML
	private Button m4;
	
	@FXML
	private Button q1;
	
	@FXML
	private Button q2; 
	
	@FXML
	private Button q3;
	
	@FXML
	private Button q4;
	
	@FXML
	private Button skip;
	
	@FXML
	private ImageView rewardTokenA;
	
	@FXML
	private ImageView rewardTokenB;

	@FXML
	private ImageView rewardTokenC;
	
	@FXML
	private ImageView rewardTokenD;
	
	@FXML
	private ImageView rewardTokenE;
	
	@FXML
	private ImageView rewardTokenF;
	
	@FXML
	private ImageView rewardTokenG;
	
	@FXML
	private ImageView rewardTokenH;

	@FXML
	private ImageView rewardTokenI;
	
	@FXML
	private ImageView rewardTokenJ;
	
	@FXML
	private ImageView rewardTokenK;
	
	@FXML
	private ImageView rewardTokenL;
	
	@FXML
	private ImageView rewardTokenM;
	
	@FXML
	private ImageView rewardTokenN;
	
	@FXML
	private ImageView rewardTokenO;
	
	@FXML
	private ImageView permitDeckSeaRegion;
	
	@FXML
	private ImageView permitDeckHillRegion;
	
	@FXML
	private ImageView permitDeckMountainRegion;
	
	@FXML
	private ImageView permitTileSea0;
	
	@FXML
	private ImageView permitTileSea1;
	
	@FXML
	private ImageView permitTileHill0;
	
	@FXML
	private ImageView permitTileHill1;
	
	@FXML
	private ImageView permitTileMountain0;
	
	@FXML
	private ImageView permitTileMountain1;
	
	@FXML
	private Pane arkon;
	
	@FXML
	private Pane burgen;
	
	@FXML
	private Pane castrum;
	
	@FXML
	private Pane dorful;
	
	@FXML
	private Pane esti;
	
	@FXML
	private Pane framek;
	
	@FXML
	private Pane graden;
	
	@FXML
	private Pane hellar;
	
	@FXML
	private Pane indur;
	
	@FXML
	private Pane juvelar;
	
	@FXML
	private Pane kultos;
	
	@FXML
	private Pane lyram;
	
	@FXML
	private Pane merkatim;
	
	@FXML
	private Pane naris;
	
	@FXML
	private Pane osium;
	
	@FXML
	private ImageView politicsDeck;
	
	@FXML
	private ImageView regionBonusSea;
	
	@FXML
	private ImageView regionBonusHill;
	
	@FXML
	private ImageView regionBonusMountain;
	
	@FXML
	private ImageView colorBonusBlue;
	
	@FXML
	private ImageView colorBonusGold;
	
	@FXML
	private ImageView colorBonusSilver;
	
	@FXML
	private ImageView colorBonusBronze;
	
	@FXML
	private ImageView kingRewardTile1;
	
	@FXML
	private ImageView kingRewardTile2;
	
	@FXML
	private ImageView kingRewardTile3;
	
	@FXML
	private ImageView kingRewardTile4;

	@FXML
	private ImageView kingRewardTile5;
	
	@FXML
	private VBox councillorReserve;
	
	@FXML
	private Label scorePlayer1;
	
	@FXML
	private Label scorePlayer2;
	
	@FXML
	private Label scorePlayer3;
	
	@FXML
	private Label scorePlayer4;
	
	@FXML
	private Label nobilityPlayer1;
	
	@FXML
	private Label nobilityPlayer2;
	
	@FXML
	private Label nobilityPlayer3;
	
	@FXML
	private Label nobilityPlayer4;
	
	@FXML
	private Label coinsPlayer1;
	
	@FXML
	private Label coinsPlayer2;
	
	@FXML
	private Label coinsPlayer3;
	
	@FXML
	private Label coinsPlayer4;
	
	@FXML
	private Label assistantsPlayer1;
	
	@FXML
	private Label assistantsPlayer2;
	
	@FXML
	private Label assistantsPlayer3;
	
	@FXML
	private Label assistantsPlayer4;
	
	@FXML
	private Label remainingEmporiumPlayer1;
	
	@FXML
	private Label remainingEmporiumPlayer2;
	
	@FXML
	private Label remainingEmporiumPlayer3;
	
	@FXML
	private Label remainingEmporiumPlayer4;
	
	@FXML
	private Label namePlayer1;
	
	@FXML
	private Label namePlayer2;
	
	@FXML
	private Label namePlayer3;
	
	@FXML
	private Label namePlayer4;
	
	@FXML
	private Label playerName;
	
	@FXML
	private Label playerScore;
	@FXML
	private Label playerCoins;
	@FXML
	private Label playerAssistants;
	@FXML
	private Label playerNobility;
	@FXML
	private HBox hand;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer1;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer2;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer3;
	
	@FXML
	private Label numberOfCoveredPermitTilePlayer4;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer1;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer2;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer3;
	
	@FXML
	private Label numberOfPoliticsCardInHandPlayer4;
	
	@FXML
	private TextArea messageBox;
	
	@FXML
	private TabPane players;

	
	public TabPane getPlayers() {
		return players;
	}

	public HBox getHand() {
		return hand;
	}

	public Pane getSeaRegion() {
		return seaRegion;
	}

	public Pane getHillRegion() {
		return hillRegion;
	}

	public Pane getMountainRegion() {
		return mountainRegion;
	}

	public Pane getKingBalcony() {
		return kingBalcony;
	}

	public TextArea getMessageBox() {
		return messageBox;
	}
	
	public Label getPlayerName() {
		return playerName;
	}


	public Label getPlayerScore() {
		return playerScore;
	}


	public Label getPlayerCoins() {
		return playerCoins;
	}


	public Label getPlayerAssistants() {
		return playerAssistants;
	}


	public Label getPlayerNobility() {
		return playerNobility;
	}


	public VBox getCouncillorReserve(){
		return councillorReserve;
	}
	
	public ImageView[] getSeaPermitTile(){
		ImageView[] array=new ImageView[2];
		array[0]=permitTileSea0;
		array[1]=permitTileSea1;
		return array;
	}
	
	public ImageView[] getHillPermitTile(){
		ImageView[] array=new ImageView[2];
		array[0]=permitTileHill0;
		array[1]=permitTileHill1;
		return array;
	}
	
	public ImageView[] getMountainPermitTile(){
		ImageView[] array=new ImageView[2];
		array[0]=permitTileMountain0;
		array[1]=permitTileMountain1;
		return array;
	}
	
	public List<ImageView> getCouncillors(RegionDTO balcony){
		switch(balcony.getName()){
		case "Sea":
			return Arrays.asList(seaConcillor1,seaConcillor2,seaConcillor3,seaConcillor4);
		case "Hill":
			return Arrays.asList(hillConcillor1,hillConcillor2,hillConcillor3,hillConcillor4);
		case "Mountain":
			return Arrays.asList(mountainConcillor1,mountainConcillor2,mountainConcillor3,mountainConcillor4);
		default:
			throw new IllegalArgumentException("Region does not exist!");
		}
	}
	
	public List<ImageView> getKingCouncillors(){
		return Arrays.asList(kingConcillor1,kingConcillor2,kingConcillor3,kingConcillor4);
	}
	
	public List<Label> getNamesLabels(){
		return Arrays.asList(namePlayer1,namePlayer2,namePlayer3,namePlayer4);
	}
	
	public List<Label> getRemainingEmporiumsLabels(){
		return Arrays.asList(remainingEmporiumPlayer1,remainingEmporiumPlayer2,remainingEmporiumPlayer3,remainingEmporiumPlayer4);
	}
	
	public List<Label> getScoreLabels(){
		return Arrays.asList(scorePlayer1,scorePlayer2,scorePlayer3,scorePlayer4);
	}
	
	public List<Label> getCoinsLabels(){
		return Arrays.asList(coinsPlayer1,coinsPlayer2, coinsPlayer3, coinsPlayer4);
	}
	
	public List<Label> getAssistantsLabels(){
		return Arrays.asList(assistantsPlayer1,assistantsPlayer2,assistantsPlayer3,assistantsPlayer4);
	}
	
	public List<Label> getNobilityLabels(){
		return Arrays.asList(nobilityPlayer1,nobilityPlayer2,nobilityPlayer3,nobilityPlayer4);
	}
	
	public ImageView getRewardToken(CityDTO city){
		switch(city.getName()){
			case "Arkon":
				return rewardTokenA;
			case "Burgen":
				return rewardTokenB;
			case "Castrum":
				return rewardTokenC;
			case "Dorful":
				return rewardTokenD;
			case "Esti":
				return rewardTokenE;
			case "Framek":
				return rewardTokenF;
			case "Graden":
				return rewardTokenG;
			case "Hellar":
				return rewardTokenH;
			case "Indur":
				return rewardTokenI;
			case "Juvelar":
				return rewardTokenJ;
			case "Kultos":
				return rewardTokenK;
			case "Lyram":
				return rewardTokenL;
			case "Merkatim":
				return rewardTokenM;
			case "Naris":
				return rewardTokenN;
			case "Osium":
				return rewardTokenO;
			default:
				throw new IllegalArgumentException("City "+city+"does not exist!");
			
		}
	}
	
	@FXML
	public void startActionPickPoliticsCard() throws RemoteException {
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof PickPoliticsCardDTO) {
				this.view.getConnection().sendAction(action);
				return;
			}
		this.view.displayError("You can't pick a politics card now");
	}

	@FXML
	public void startActionElectCouncillor() {
		ElectCouncillorDTO selectedAction=new ElectCouncillorDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof ElectCouncillorDTO) {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionAcquirePermitTile() {
		AcquirePermitTileDTO selectedAction=new AcquirePermitTileDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof AcquirePermitTileDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionBuildByPermitTile() {
		BuildByPermitTileDTO selectedAction=new BuildByPermitTileDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof BuildByPermitTileDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void clickOnActionBuildByKing() {
		BuildByKingDTO selectedAction=new BuildByKingDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof BuildByKingDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionEngageAssistant() throws RemoteException {
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof EngageAssistantDTO) {
				this.view.getConnection().sendAction(action);
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionChangePermitTiles() {
		ChangePermitTilesDTO selectedAction=new ChangePermitTilesDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof ChangePermitTilesDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionElectCouncillorByAssistant() {
		ElectCouncillorByAssistantDTO selectedAction=new ElectCouncillorByAssistantDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof ElectCouncillorByAssistantDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionAdditionalMainAction() throws RemoteException {
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof AddictionalMainActionDTO) {
				this.view.getConnection().sendAction(action);
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	
	@FXML
	public void startActionSkip() throws RemoteException {
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof MoveToNextDTO) {
				this.view.getConnection().sendAction(action);
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	
	@FXML
	public void startActionMakeAnOffer() {
		MakeAnOfferDTO selectedAction=new MakeAnOfferDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof MakeAnOfferDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("You cant't make an offer now!");
	}
	
	@FXML
	public void startActionAcceptAnOffer() {
		MakeAnOfferDTO selectedAction=new MakeAnOfferDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof AcceptAnOfferDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
					}
				});
				return;
			}
		this.view.displayError("You cant't accept an offer now!");
	}
	
	
	
	@FXML
	public void clickOnRegionSea() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(0));
			this.notify();
		}
	}

	@FXML
	public void clickOnRegionHill() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(1));
			this.notify();
		}
	}

	@FXML
	public void clickOnRegionMountain() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(2));
			this.notify();
		}
	}
	
	@FXML
	public void clickOnPermitTileRegionSea1() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(0).getUncoveredPermitTiles()[0]);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnPermitTileRegionSea2() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(0).getUncoveredPermitTiles()[1]);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnPermitTileRegionHill1() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(1).getUncoveredPermitTiles()[0]);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnPermitTileRegionHill2() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(1).getUncoveredPermitTiles()[1]);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnPermitTileRegionMountain1() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(2).getUncoveredPermitTiles()[0]);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnPermitTileRegionMountain2() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(2).getUncoveredPermitTiles()[1]);
			this.notify();
		}
	}
	

	@FXML
	public void clickOnKingBalcony() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientKingBalcony());
			this.notify();
		}
		
	}

	@FXML
	public void clickOnReserveCouncillor1() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(0));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor2() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(1));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor3() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(2));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor4() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(3));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor5() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(4));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor6() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(5));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor7() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(6));
			this.notify();
		}
	}

	@FXML
	public void clickOnReserveCouncillor8() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(7));
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityArkon() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Arkon".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityBurgen() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Burgen".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityCastrum() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Castrum".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityDorful() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Dorful".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityEsti() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Esti".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityFramek() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(1);
			for (CityDTO city : region.getCities())
				if ("Framek".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityGraden() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(1);
			for (CityDTO city : region.getCities())
				if ("Graden".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityHellar() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(1);
			for (CityDTO city : region.getCities())
				if ("Hellar".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityIndur() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(1);
			for (CityDTO city : region.getCities())
				if ("Indur".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityJuvelar() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(1);
			for (CityDTO city : region.getCities())
				if ("Juvelar".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityKultos() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(2);
			for (CityDTO city : region.getCities())
				if ("Kultos".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityLyram() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(2);
			for (CityDTO city : region.getCities())
				if ("Lyram".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityMerkatim() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(2);
			for (CityDTO city : region.getCities())
				if ("Merkatim".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityNaris() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(2);
			for (CityDTO city : region.getCities())
				if ("Naris".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCityOsium() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(2);
			for (CityDTO city : region.getCities())
				if ("Osium".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}

}
