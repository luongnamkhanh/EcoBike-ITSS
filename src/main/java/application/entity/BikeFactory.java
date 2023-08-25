package application.entity;

public class BikeFactory {

    public Bike createBike(String bikeType) {
        switch (bikeType) {
            case "EBike":
                return new EBike();
            case "TwinBike":
                return new TwinBike();
            default:
                return new Bike(bikeType);  
        }
    }
}

