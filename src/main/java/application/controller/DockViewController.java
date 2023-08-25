package application.controller;

import static application.util.Setting.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.entity.Dock;
import application.util.OpenNewScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DockViewController implements Initializable {
	@FXML
	private Text tx00, tx01, tx02, tx03, tx04, tx05, tx06, tx07, tx08;
	@FXML
	private TextField searchBarField;
	@FXML
	private VBox vb0, vb1, vb2, vb3, vb4, vb5, vb6, vb7, vb8;
	@FXML
	private Button searchButton;
	private Text tx[];
	
	@FXML
	private Button bt1;
	
	private DockController dockController;
	private int selectedRow = -1, selectedCol = -1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tx = new Text[]{tx00, tx01, tx02, tx03, tx04, tx05, tx06, tx07, tx08};
	};
	
	public void displayDocks() {
		List<Dock> listOfDocks = dockController.getAllDock();
		for (int i = 0; i < NUMBER_OF_DOCKS; i++) {
			tx[i].setText("");
		}
		System.out.println(listOfDocks.size());
		for (int i = 0; i < listOfDocks.size(); i++) 
		{
			
			tx[i].setText(listOfDocks.get(i).getDockStatus());
		}
	}
	
	public void displayDocks(List<Dock> listOfDocks) {
		for (int i = 0; i < NUMBER_OF_DOCKS; i++) {
			tx[i].setText("");
		}
		
		for (int i = 0; i < listOfDocks.size(); i++) 
		{
			tx[i].setText(listOfDocks.get(i).getDockStatus());
		}
	}
	
	@FXML
	private void search() {
		displayDocks(dockController.searchDocks(searchBarField.getText()));
		System.out.println("Searching\n");
	}
	
	public void setDockController(DockController dockController) {
		this.dockController = dockController;
	}
	
	@FXML
	private void goToDockInfoView(ActionEvent event) throws IOException {
		int index = selectedRow * HORIZONTAL_DISPLAY + selectedCol;
		if (index < 0 || index >= dockController.getAllDock().size())
			return;
		
		Dock dock = dockController.getAllDock().get(index); 
		
		FXMLLoader loader = OpenNewScene.inOldWindow(DOCK_INFO_VIEW_FXML, event, this);
		DockInfoViewController dockInfoViewController = loader.getController();
		dockInfoViewController.display(dock);
		dockInfoViewController.setPreviosScene(searchButton.getScene());
	}
	
	@FXML
	private void onClick(MouseEvent event) {
		System.out.printf("Clicked");
		Node source = (Node)event.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        colIndex = colIndex == null ? 0 : colIndex;
        rowIndex = rowIndex == null ? 0 : rowIndex;
        System.out.printf("Clicked on cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
        selectedCol = colIndex;
        selectedRow = rowIndex;
	}
	
}
