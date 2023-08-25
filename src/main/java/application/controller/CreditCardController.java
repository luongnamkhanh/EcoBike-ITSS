package application.controller;

import static application.util.Setting.*;

import application.entity.PaymentTransaction;
import application.entity.Bike;
import application.entity.CreditCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.util.OpenNewScene;

import static application.util.OpenNewScene.inOldWindow;


import java.io.IOException;

public class CreditCardController {

    private Bike bike;
    private double rentedFees;


    public void setRentedFees(double rentedFees) {
        this.rentedFees = rentedFees;
    }

    @FXML
    private TextField cardHolderNameField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField issuingBankField;
    @FXML
    private TextField expirationDateField;
    @FXML
    private TextField securityCodeField;

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        String cardHolderName = cardHolderNameField.getText();
        String cardNumber = cardNumberField.getText();
        String issuingBank = issuingBankField.getText();
        String expirationDate = expirationDateField.getText();
        String securityCode = securityCodeField.getText();
        // Create a new CreditCard instance with the retrieved values
        CreditCard card = new CreditCard(cardHolderName, cardNumber, securityCode, expirationDate, issuingBank);

        PaymentTransaction paymentTransaction;
        FXMLLoader loader;
        if (this.bike.isStatus()) {
            paymentTransaction = new PaymentTransaction("Rent", bike, card);
            System.out.println(paymentTransaction.getCard().getCardNumber());
            loader = inOldWindow(PAYMENT_TRANSACTION_VIEW_FXML, event, this);
        } else {
            paymentTransaction = new PaymentTransaction("Return", bike, card);
            loader = inOldWindow(PAYMENT_TRANSACTION_RETURNING_VIEW_FXML, event, this);
        }


        PaymentTransactionController paymentTransactionController = loader.getController();
        paymentTransactionController.setPaymentTransaction(paymentTransaction, bike, rentedFees);
    }

    @FXML
    void returnToPreviousScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(BIKE_DETAIL_VIEW_FXML));


        Parent root = loader.load();

        BikeDetailViewController bikeDetailViewController = loader.getController();
        bikeDetailViewController.displayBikeDetail(this.bike);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close(); // Close the current stage

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Bike Detail information");
        stage.show();
    }
}



