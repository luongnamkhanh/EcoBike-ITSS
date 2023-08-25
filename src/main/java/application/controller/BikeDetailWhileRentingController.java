package application.controller;

import static application.util.Setting.RETURN_VIEW_FXML;

import java.io.IOException;
//import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

import application.dao.DockDAO;
import application.entity.Bike;
//import application.entity.Bike.BikeType;
import application.entity.Dock;
import application.util.OpenNewScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;;

public class BikeDetailWhileRentingController {
    private Bike bike;

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    @FXML
    TextArea bikeInfoWhileRenting;

    @FXML
    ImageView bikeImg;

    @FXML
    Text timer;

    Time time = new Time("0:0:0");
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                time.oneSecondPassed();
                timer.setText(time.getCurrentTime());
            })
    );

    public void startToCount() {

//    	Time time = new Time("0:0:0");
        timer.setText(time.getCurrentTime());


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public void displayBikeInfo(Bike bike) {
        this.bike = bike;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = bike.getRentedTime().format(formatter);

        String bikeInfo = "Brand: " + bike.getBrand() + "\n" +
                "Type: " + bike.getBikeType() + "\n" +
                "License Plate: " + bike.getPlate() + "\n" +
                "Renting time: " + formattedDateTime + "\n" +
                "Remaining battery: 100%" + "\n" +
                bike.getAdditionalInfo();

        bikeInfoWhileRenting.setText(bikeInfo);
        setImageBasedOnBikeType(bike.getBikeType());
    }


    private void setImageBasedOnBikeType(String bikeType) {
        String imagePath = "/application/pictures/" + bikeType + ".png";
        System.out.println("Loading image from: " + imagePath);
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        bikeImg.setImage(image);
    }

    @FXML
    void returnBike(ActionEvent event) throws IOException {

        FXMLLoader loader = OpenNewScene.inOldWindow(RETURN_VIEW_FXML, event, this);

        IReturnBike returnBikeType;
        if (bike.getBikeType().compareTo("TwinBike") == 0) {
            returnBikeType = new ReturnTwinBike();
        } else if (bike.getBikeType().compareTo("StandardBike") == 0) {
            returnBikeType = new ReturnStdBike();
        } else if (bike.getBikeType().compareTo("EBike") == 0) {
            returnBikeType = new ReturnEBike();
        } else {
            returnBikeType = new ReturnStdBike();
        }

        System.out.print("running in return view\n");
        System.out.println("bike info: " + bike.getBikeId() + " type: " + bike.getBrand() + " rented time: " + bike.getRentedTime());

        ReturnController returner = new ReturnController(returnBikeType, bike);
        ReturnViewController returnViewController;

        try {
            DockDAO dockDAO = new DockDAO();
            List<Dock> dockList = dockDAO.getAllDocks();
            for (Dock dock : dockList) {
                returner.addDockToList(dock);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        returnViewController = loader.getController();
        returnViewController.setReturnController(returner);
        returnViewController.displayDocks();

    }

}
