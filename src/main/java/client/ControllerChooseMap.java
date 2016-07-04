package client;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.view.GUI;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Is the controller class for the screen where the first player chooses tha map.
 * @author cg31
 *
 */
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
	@FXML
	private ImageView preview;
	@FXML
	private AnchorPane backGround;

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

	public ImageView getPreview() {
		return preview;
	}

	@FXML
	public void changeMouseStyle(){
	Platform.runLater(() -> {
		Image image = new Image(getClass().getResource("view/images/cursor/arrow.png").toExternalForm());
		backGround.setCursor(new ImageCursor(image));
		});
    }
	
	@FXML
    public void clickOnMap(Event event){
		int mapNumber=(int)((ImageView) event.getSource()).getUserData();
    	try {
			view.getConnection().sendAction(new ChooseMapDTO(mapNumber));
		} catch (RemoteException e) {
			Logger logger=Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "Send action RMI failes", e);
		}
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Map Chosen");
    	alert.setHeaderText("You have chosen map number "+mapNumber+".\nPress OK to continue.");
    	alert.showAndWait();
    	chooseMapStage.close();
    }
	
	@FXML
    public void changeMouseStyleMap1(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
    		preview.setImage(new Image(getClass().getResource("view/images/various/map1copia.jpg").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        });
    }
	
	@FXML
    public void changeMouseStyleMap2(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
    		preview.setImage(new Image(getClass().getResource("view/images/various/map2copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        });
    }
	
	@FXML
    public void changeMouseStyleMap3(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
	    	preview.setImage(new Image(getClass().getResource("view/images/various/map3copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        });
    }
	
	@FXML
    public void changeMouseStyleMap4(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
	    	preview.setImage(new Image(getClass().getResource("view/images/various/map4copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	       });
    }
	
	@FXML
    public void changeMouseStyleMap5(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
	    	preview.setImage(new Image(getClass().getResource("view/images/various/map5copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        });
    }
	
	@FXML
    public void changeMouseStyleMap6(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
	    	preview.setImage(new Image(getClass().getResource("view/images/various/map6copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        });
    }
	
	@FXML
    public void changeMouseStyleMap7(MouseEvent mouseEvent) {
    	Platform.runLater(() -> {
	    	preview.setImage(new Image(getClass().getResource("view/images/various/map7copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	    	});
    }
	
	@FXML
    public void changeMouseStyleMap8(MouseEvent mouseEvent) {
    	Platform.runLater(()-> {
	    	preview.setImage(new Image(getClass().getResource("view/images/various/map8copia.png").toExternalForm()));
	    	((Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
	        });
    }

	@FXML
    public void removeImageOnMouseExited() {
    	Platform.runLater(()-> 
	    		preview.setImage(new Image(getClass().getResource("view/images/various/scritta.png").toExternalForm()))
	        );
    }
}

