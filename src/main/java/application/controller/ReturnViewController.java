package application.controller;

import static application.util.Setting.DOCK_INFO_VIEW_FXML;
import static application.util.Setting.HORIZONTAL_DISPLAY;
import static application.util.Setting.NUMBER_OF_DOCKS;
import static application.util.Setting.CARD_INFORMATION_VIEW_FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.entity.Dock;
import application.entity.Bike;
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

public class ReturnViewController implements Initializable{
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
	
	private ReturnController returnController;
	private int selectedRow = -1, selectedCol = -1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tx = new Text[]{tx00, tx01, tx02, tx03, tx04, tx05, tx06, tx07, tx08};
	};
	
	public void displayDocks() {
		List<Dock> listOfDocks = returnController.getAllDock();
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
	
	// may need this
	@FXML
	private void search() {
		displayDocks(returnController.searchDocks(searchBarField.getText()));
		System.out.println("Searching\n");
	}
	
	public void setReturnController(ReturnController returnController) {
		this.returnController = returnController;
	}
	
	// no need this
	@FXML
	private void getChoosenDock(ActionEvent event) throws IOException {
		int index = selectedRow * HORIZONTAL_DISPLAY + selectedCol;
		if (index < 0 || index >= returnController.getAllDock().size())
			return;
		
		Dock dock = returnController.getAllDock().get(index); 
		Bike bike = returnController.getBike();
		
		System.out.println("bike info in return view: "+ bike.getBikeId() + " type: " + bike.getBrand() + " rented time: " + bike.getRentedTime());
		double rentedFees = returnController.proceedReturnBike(dock);
		
		FXMLLoader loader = OpenNewScene.inOldWindow(CARD_INFORMATION_VIEW_FXML, event, this);

		CreditCardController creditCardController = loader.getController();
        creditCardController.setBike(returnController.getBike());
        creditCardController.setRentedFees(rentedFees);
		
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
