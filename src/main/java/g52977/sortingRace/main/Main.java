package g52977.sortingRace.main;

import g52977.sortingRace.controller.Controller;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import g52977.sortingRace.model.Model;
import g52977.sortingRace.model.ModelInterface;
import g52977.sortingRace.view.FXMLController;

/**
 * Main class of the application.
 * 
 * @author 52977
 */
public class Main extends Application {        
    
    /**
     * Launches the application.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Starts the application.
     * 
     * @param stage The stage of the application.
     * @throws Exception An exception if the start fail.
     */
    @Override
    public void start(Stage stage) throws Exception {           
        ModelInterface model = new Model();				
	FXMLController fxmlController = new FXMLController(stage);
        Controller controller = new Controller(model);
	fxmlController.setController(controller);
	fxmlController.showStage();
    }
    
}