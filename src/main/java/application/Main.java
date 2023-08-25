package application;

import static application.util.Setting.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import application.controller.DockController;
import application.controller.DockViewController;
import application.dao.DockDAO;
import application.entity.Bike;
import application.entity.Dock;
import application.util.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	DockController dockController = new DockController();
	DockViewController dockViewController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
	        DockDAO dockDAO = new DockDAO();
	        List<Dock> dockList = dockDAO.getAllDocks();
	        for (Dock dock : dockList) {
	            dockController.addDockToList(dock);
	        }
		} catch (Exception e) { 
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(DOCK_VIEW_FXML));
			Parent root = loader.load();
			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			dockViewController = loader.getController();
			dockViewController.setDockController(dockController);
			dockViewController.displayDocks();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
