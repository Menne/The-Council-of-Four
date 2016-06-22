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
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.GUI;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private HBox emporiumsArkon;

	@FXML
	private HBox emporiumsBurgen;

	@FXML
	private HBox emporiumsCastrum;

	@FXML
	private HBox emporiumsDorful;

	@FXML
	private HBox emporiumsEsti;

	@FXML
	private HBox emporiumsFramek;

	@FXML
	private HBox emporiumsGraden;

	@FXML
	private HBox emporiumsHellar;

	@FXML
	private HBox emporiumsIndur;

	@FXML
	private HBox emporiumsJuvelar;

	@FXML
	private HBox emporiumsKultos;

	@FXML
	private HBox emporiumsLyram;

	@FXML
	private HBox emporiumsMerkatim;

	@FXML
	private HBox emporiumsNaris;

	@FXML
	private HBox emporiumsOsium;

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
	private HBox permitTilesTurnedUpPlayer1;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer2;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer3;
	
	@FXML
	private HBox permitTilesTurnedUpPlayer4;
	
	@FXML
	private HBox permitTilesTurnedUpOwned;
	
	@FXML
	private HBox permitTilesTurnedDownOwned;
	
	@FXML
	private Button descardPoliticsCards;
	
	@FXML
	private VBox playersBonuses;
	
	@FXML
	private HBox genericPlayerBonuses1;
	
	@FXML
	private HBox genericPlayerBonuses2;
	
	@FXML
	private HBox genericPlayerBonuses3;
	
	@FXML
	private HBox genericPlayerBonuses4;
	
	public List<HBox> getGenericPlayerBonuses(){
		return Arrays.asList(genericPlayerBonuses1,genericPlayerBonuses2,genericPlayerBonuses3,genericPlayerBonuses4);
	}
	
	public VBox getPlayersBonuses() {
		return playersBonuses;
	}

	public HBox getPermitTilesTurnedUpOwned() {
		return permitTilesTurnedUpOwned;
	}

	public HBox getPermitTilesTurnedDownOwned() {
		return permitTilesTurnedDownOwned;
	}

	public List<HBox> getPermitTilesOtherPlayers(){
		return Arrays.asList(permitTilesTurnedUpPlayer1,permitTilesTurnedUpPlayer2,permitTilesTurnedUpPlayer3,permitTilesTurnedUpPlayer4);
	}

	public List<Pane> getCities(){
		return Arrays.asList(arkon,burgen,castrum,dorful,esti,framek,graden,hellar,indur,kultos,juvelar,lyram,merkatim,naris,osium);
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

	public Pane getSeaBalcony() {
		return seaBalcony;
	}

	public Pane getHillBalcony() {
		return hillBalcony;
	}

	public Pane getMountainBalcony() {
		return mountainBalcony;
	}

	public Pane getKingBalcony() {
		return kingBalcony;
	}

	public Pane getArkon() {
		return arkon;
	}

	public Pane getBurgen() {
		return burgen;
	}

	public Pane getCastrum() {
		return castrum;
	}

	public Pane getDorful() {
		return dorful;
	}

	public Pane getEsti() {
		return esti;
	}

	public Pane getFramek() {
		return framek;
	}

	public Pane getGraden() {
		return graden;
	}

	public Pane getHellar() {
		return hellar;
	}

	public Pane getIndur() {
		return indur;
	}

	public Pane getJuvelar() {
		return juvelar;
	}

	public Pane getKultos() {
		return kultos;
	}

	public Pane getLyram() {
		return lyram;
	}

	public Pane getMerkatim() {
		return merkatim;
	}

	public Pane getNaris() {
		return naris;
	}

	public Pane getOsium() {
		return osium;
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
	


	public List<ImageView> getCouncillorReserve(){
		return Arrays.asList(reserveConcillor1,reserveConcillor2,reserveConcillor3,reserveConcillor4,reserveConcillor5,reserveConcillor6,reserveConcillor7,reserveConcillor8);
	}
	
	public List<Label> getNumberOfPoliticsCards(){
		return Arrays.asList(numberOfPoliticsCardInHandPlayer1,numberOfPoliticsCardInHandPlayer2,numberOfPoliticsCardInHandPlayer3,numberOfPoliticsCardInHandPlayer4);
	}
	
	public List<Label> getNumberOfCoveredPermitTiles(){
		return Arrays.asList(numberOfCoveredPermitTilePlayer1,numberOfCoveredPermitTilePlayer2,numberOfCoveredPermitTilePlayer3,numberOfCoveredPermitTilePlayer4);
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
	
	public HBox getEmporiumsArkon() {
		return emporiumsArkon;
	}

	public HBox getEmporiumsBurgen() {
		return emporiumsBurgen;
	}

	public HBox getEmporiumsCastrum() {
		return emporiumsCastrum;
	}

	public HBox getEmporiumsDorful() {
		return emporiumsDorful;
	}

	public HBox getEmporiumsEsti() {
		return emporiumsEsti;
	}

	public HBox getEmporiumsFramek() {
		return emporiumsFramek;
	}

	public HBox getEmporiumsGraden() {
		return emporiumsGraden;
	}

	public HBox getEmporiumsHellar() {
		return emporiumsHellar;
	}

	public HBox getEmporiumsIndur() {
		return emporiumsIndur;
	}

	public HBox getEmporiumsJuvelar() {
		return emporiumsJuvelar;
	}

	public HBox getEmporiumsKultos() {
		return emporiumsKultos;
	}

	public HBox getEmporiumsLyram() {
		return emporiumsLyram;
	}

	public HBox getEmporiumsMerkatim() {
		return emporiumsMerkatim;
	}

	public HBox getEmporiumsNaris() {
		return emporiumsNaris;
	}

	public HBox getEmporiumsOsium() {
		return emporiumsOsium;
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
	
	public Button getDescardPoliticsCards() {
		return descardPoliticsCards;
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
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionAcquirePermitTile() throws RemoteException {
		AcquirePermitTileDTO selectedAction=new AcquirePermitTileDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof AcquirePermitTileDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				return;
			}
		this.view.displayError("Sorry, action not available!");
	}
	
	@FXML
	public void startActionBuildByKing() {
		BuildByKingDTO selectedAction=new BuildByKingDTO();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			if (action instanceof BuildByKingDTO) {
				ExecutorService executor=Executors.newSingleThreadExecutor();
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						selectedAction.setParser().setParameters(view, clientGame);
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						try {
							view.getConnection().sendAction(selectedAction);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				return;
			}
		this.view.displayError("You cant't accept an offer now!");
	}
	
	
	@FXML
	public void clickOnRegion(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((Pane) event.getSource()).getUserData());
			this.notify();
		}
	}
	
	
	@FXML
	public void clickOnPermitTileInRegion(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((ImageView) event.getSource()).getUserData());
			this.notify();
		}
	}
	
	
	@FXML
	public void clickOnBalcony(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((Pane) event.getSource()).getUserData());
			this.notify();
		}
	}
	
	@FXML
	public void clickOnCouncillorsReserve(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((ImageView) event.getSource()).getUserData());
			this.notify();
		}
	}
		
	@FXML
	public void clickOnCity(Event event) {
		synchronized (this) {
			view.setCurrentParameter(((Pane) event.getSource()).getUserData());
			this.notify();
		}
	}

	@FXML
	public void stopDiscarding(){
		synchronized (this) {
			this.view.setCurrentParameter("stop");
			this.notify();
		}
	}
	
	public void handlePoliticsCard(PoliticsCardDTO selectedCard) {
		synchronized (this) {
			this.view.setCurrentParameter(selectedCard);
			this.notify();
		}
	}

   
    @FXML
    public void changeMouseStyle(MouseEvent mouseEvent) {
    	Platform.runLater(new Runnable() {
	        
	    	@Override
	        public void run() {
	    		((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        }
	    });
    }

}