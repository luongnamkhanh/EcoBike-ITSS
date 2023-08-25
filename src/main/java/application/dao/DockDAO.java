package application.dao;

import application.entity.Dock;
import application.entity.EBike;
import application.entity.BikeFactory;
import application.entity.Bike;
import application.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DockDAO {

    public List<Dock> getAllDocks() {
        List<Dock> docks = new ArrayList<>();
        String dockQuery = "SELECT * FROM docks";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement dockPstmt = conn.prepareStatement(dockQuery)) {

            ResultSet dockRs = dockPstmt.executeQuery();
            while (dockRs.next()) {
                Dock dock = new Dock();
                dock.setDockId(dockRs.getInt("dock_id"));
                dock.setName(dockRs.getString("name"));
                dock.setAddress(dockRs.getString("address"));
                dock.setDockSize(dockRs.getInt("dock_size"));
                List<Bike> bikesForThisDock = getBikesByDockId(dockRs.getInt("dock_id"));
                for(Bike bike : bikesForThisDock) {
                    dock.addBikeToDock(bike);
                }
                docks.add(dock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docks;
    }

    private List<Bike> getBikesByDockId(int dockId) {
        List<Bike> bikes = new ArrayList<>();
        String bikesQuery = "SELECT * FROM bikes WHERE dockId = ?";
        
        BikeFactory bikeFactory = new BikeFactory();
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement bikesPstmt = conn.prepareStatement(bikesQuery)) {

            bikesPstmt.setInt(1, dockId);
            ResultSet bikesRs = bikesPstmt.executeQuery();
            while (bikesRs.next()) {
            	String bikeType = bikesRs.getString("bikeType");
                Bike bike = bikeFactory.createBike(bikeType);
                bike.setBikeId(bikesRs.getInt("bike_id"));
                bike.setPlate(bikesRs.getString("plate"));
                bike.setPrice(bikesRs.getInt("price"));
                bike.setRentingTime(bikesRs.getFloat("rentingTime"));
                if(bike instanceof EBike) {
                    ((EBike)bike).setBatteryPercentage(bikesRs.getFloat("batteryPercentage"));
                    ((EBike)bike).setTimeRemain(bikesRs.getFloat("timeRemain"));
                }
                bike.setBikeCode(bikesRs.getString("bikeCode"));
                bike.setBrand(bikesRs.getString("brand"));
                Timestamp ts = bikesRs.getTimestamp("rentedTime");
                if (ts != null) {
                	bike.setRentedTime(ts.toLocalDateTime());
                }
                bikes.add(bike);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bikes;
    }
}
