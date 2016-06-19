package client;

import java.rmi.RemoteException;

import client.modelDTO.GameDTO;
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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
	private ImageView kingBalcony;
	
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
	private ImageView RewardTokenA;
	
	@FXML
	private ImageView RewardTokenB;

	@FXML
	private ImageView RewardTokenC;
	
	@FXML
	private ImageView RewardTokenD;
	
	@FXML
	private ImageView RewardTokenE;
	
	@FXML
	private ImageView RewardTokenF;
	
	@FXML
	private ImageView RewardTokenG;
	
	@FXML
	private ImageView RewardTokenH;

	@FXML
	private ImageView RewardTokenI;
	
	@FXML
	private ImageView RewardTokenJ;
	
	@FXML
	private ImageView RewardTokenK;
	
	@FXML
	private ImageView RewardTokenL;
	
	@FXML
	private ImageView RewardTokenM;
	
	@FXML
	private ImageView RewardTokenN;
	
	@FXML
	private ImageView RewardTokenO;
	
	@FXML
	private ImageView PermitDeckSeaRegion;
	
	@FXML
	private ImageView PermitDeckHillRegion;
	
	@FXML
	private ImageView PermitDeckMountainRegion;
	
	@FXML
	private ImageView PermitTileSea0;
	
	@FXML
	private ImageView PermitTileSea1;
	
	@FXML
	private ImageView PermitTileHill0;
	
	@FXML
	private ImageView PermitTileHill1;
	
	@FXML
	private ImageView PermitTileMountain0;
	
	@FXML
	private ImageView PermitTileMountain1;
	
	@FXML
	private Pane Arkon;
	
	@FXML
	private Pane Burgen;
	
	@FXML
	private Pane Castrum;
	
	@FXML
	private Pane Dorful;
	
	@FXML
	private Pane Esti;
	
	@FXML
	private Pane Framek;
	
	@FXML
	private Pane Graden;
	
	@FXML
	private Pane Hellar;
	
	@FXML
	private Pane Indur;
	
	@FXML
	private Pane Juvelar;
	
	@FXML
	private Pane Kultos;
	
	@FXML
	private Pane Lyram;
	
	@FXML
	private Pane Merkatim;
	
	@FXML
	private Pane Naris;
	
	@FXML
	private Pane Osium;
	@FXML
	private TextArea messageBox;
	
	public Pane getSeaRegion() {
		return seaRegion;
	}

	public Pane getHillRegion() {
		return hillRegion;
	}

	public Pane getMountainRegion() {
		return mountainRegion;
	}

	public ImageView getKingBalcony() {
		return kingBalcony;
	}

	public ImageView getSeaConcillor1() {
		return seaConcillor1;
	}

	public ImageView getSeaConcillor2() {
		return seaConcillor2;
	}

	public ImageView getSeaConcillor3() {
		return seaConcillor3;
	}

	public ImageView getSeaConcillor4() {
		return seaConcillor4;
	}

	public ImageView getHillConcillor1() {
		return hillConcillor1;
	}

	public ImageView getHillConcillor2() {
		return hillConcillor2;
	}

	public ImageView getHillConcillor3() {
		return hillConcillor3;
	}

	public ImageView getHillConcillor4() {
		return hillConcillor4;
	}

	public ImageView getMountainConcillor1() {
		return mountainConcillor1;
	}

	public ImageView getMountainConcillor2() {
		return mountainConcillor2;
	}

	public ImageView getMountainConcillor3() {
		return mountainConcillor3;
	}

	public ImageView getMountainConcillor4() {
		return mountainConcillor4;
	}

	public ImageView getKingConcillor1() {
		return kingConcillor1;
	}

	public ImageView getKingConcillor2() {
		return kingConcillor2;
	}

	public ImageView getKingConcillor3() {
		return kingConcillor3;
	}

	public ImageView getKingConcillor4() {
		return kingConcillor4;
	}

	public ImageView getReserveConcillor1() {
		return reserveConcillor1;
	}

	public ImageView getReserveConcillor2() {
		return reserveConcillor2;
	}

	public ImageView getReserveConcillor3() {
		return reserveConcillor3;
	}

	public ImageView getReserveConcillor4() {
		return reserveConcillor4;
	}

	public ImageView getReserveConcillor5() {
		return reserveConcillor5;
	}

	public ImageView getReserveConcillor6() {
		return reserveConcillor6;
	}

	public ImageView getReserveConcillor7() {
		return reserveConcillor7;
	}

	public ImageView getReserveConcillor8() {
		return reserveConcillor8;
	}

	public Button getM1() {
		return m1;
	}

	public Button getM2() {
		return m2;
	}

	public Button getM3() {
		return m3;
	}

	public Button getM4() {
		return m4;
	}

	public Button getQ1() {
		return q1;
	}

	public Button getQ2() {
		return q2;
	}

	public Button getQ3() {
		return q3;
	}

	public Button getQ4() {
		return q4;
	}

	public Button getSkip() {
		return skip;
	}

	public TextArea getMessageBox() {
		return messageBox;
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
	
	
	
	

	@FXML
	public void startM1() {
		ElectCouncillorDTO action=new ElectCouncillorDTO();
		action.setParser().setParameters(this.view, this.clientGame);
	}
	
	@FXML
	public void startM2() {
		AcquirePermitTileDTO action=new AcquirePermitTileDTO();
		ExecutorService executor=Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				action.setParser().setParameters(view, clientGame);
				
			}
		});
	}
	
	@FXML
	public void startM3() {
		BuildByPermitTileDTO action=new BuildByPermitTileDTO();
		action.setParser().setParameters(this.view, this.clientGame);
	}
	
	@FXML
	public void startM4() {
		BuildByKingDTO action=new BuildByKingDTO();
		action.setParser().setParameters(this.view, this.clientGame);
	}
	
	@FXML
	public void startQ1() throws RemoteException {
		EngageAssistantDTO action=new EngageAssistantDTO();
		this.view.getConnection().sendAction(action);
	}
	
	@FXML
	public void startQ2() {
		ChangePermitTilesDTO action=new ChangePermitTilesDTO();
		action.setParser().setParameters(this.view, this.clientGame);
	}
	
	@FXML
	public void startQ3() {
		ElectCouncillorByAssistantDTO action=new ElectCouncillorByAssistantDTO();
		action.setParser().setParameters(this.view, this.clientGame);
	}
	
	@FXML
	public void startQ4() throws RemoteException {
		AddictionalMainActionDTO action=new AddictionalMainActionDTO();
		this.view.getConnection().sendAction(action);
	}
	
	
	@FXML
	public void clickOnSeaRegion() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(0));
			this.notify();
		}
	}

	@FXML
	public void clickOnHillRegion() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(1));
			this.notify();
		}
	}

	@FXML
	public void clickOnMountainRegion() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientRegions().get(2));
			this.notify();
		}
	}

	@FXML
	public void clickOnKingBlacony() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientKingBalcony());
			this.notify();
		}
		
	}

	@FXML
	public void reserveConcillor1Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(0));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor2Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(1));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor3Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(2));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor4Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(3));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor5Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(4));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor6Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(5));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor7Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(6));
			this.notify();
		}
	}

	@FXML
	public void reserveConcillor8Clicked() {
		synchronized (this) {
			view.setCurrentParameter(clientGame.getClientGameTable().getClientCouncillorReserve().get(7));
			this.notify();
		}
	}
	
	@FXML
	public void arkonClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Arkon".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void burgenClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Burgen".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void castrumClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Castrum".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void dorfulClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Dorful".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void eskiClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Eski".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void framekClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Framek".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void gradenClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Graden".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void hellarClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Hellar".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void indurClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Indur".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void juvelarClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Juvelar".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void kultosClicked() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Kultos".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnLyram() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Lyram".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnMerkatim() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Merkatim".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnNaris() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Naris".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}
	
	@FXML
	public void clickOnOsium() {
		synchronized (this) {
			RegionDTO region=clientGame.getClientGameTable().getClientRegions().get(0);
			for (CityDTO city : region.getCities())
				if ("Osium".equals(city.getName()))
					view.setCurrentParameter(city);
			this.notify();
		}
	}

}
