package client;

import java.rmi.RemoteException;

import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.view.GUI;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerChooseMap {
	private GUI view;
	private Stage chooseMapStage;
	
	@FXML
	private ImageView map1;
	@FXML
	private ImageView map2;	
	@FXML
	private ImageView map3;	
	@FXML
	private ImageView map4;
	@FXML
	private ImageView map5;
	@FXML
	private ImageView map6;
	@FXML
	private ImageView map7;
	@FXML
	private ImageView map8;
	

	public ImageView getMap1() {
		return map1;
	}

	public ImageView getMap2() {
		return map2;
	}

	public ImageView getMap3() {
		return map3;
	}

	public ImageView getMap4() {
		return map4;
	}

	public ImageView getMap5() {
		return map5;
	}

	public ImageView getMap6() {
		return map6;
	}

	public ImageView getMap7() {
		return map7;
	}

	public ImageView getMap8() {
		return map8;
	}

	public void setView(GUI view) {
		this.view = view;
	}
	
    public Stage getChooseMapStage() {
		return chooseMapStage;
	}

	public void setChooseMapStage(Stage chooseMapStage) {
		this.chooseMapStage = chooseMapStage;
	}

	@FXML
    public void clickOnMap(Event event){
		int mapNumber=(int)((ImageView) event.getSource()).getUserData();
    	try {
			view.getConnection().sendAction(new ChooseMapDTO(mapNumber));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Map Chosen");
    	alert.setHeaderText("You have chosen map number "+mapNumber+".\nPress OK to continue.");
    	alert.showAndWait();
    	chooseMapStage.close();
    }
}
