package application.dao;

import application.entity.BikeFactory;
import application.entity.EBike;
import application.entity.Bike;
import application.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BikeDAO {

    public Bike getBikeById(int id) {
        Bike bike = null;
        String query = "SELECT * FROM bikes WHERE bike_id = ?";
        BikeFactory bikeFactory = new BikeFactory();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String bikeType = rs.getString("bikeType");
                bike = bikeFactory.createBike(bikeType);
                bike.setRentingTime(rs.getFloat("rentingTime"));
                if(bike instanceof EBike) {
                    ((EBike)bike).setBatteryPercentage(rs.getFloat("batteryPercentage"));
                    ((EBike)bike).setTimeRemain(rs.getFloat("timeRemain"));
                }

                bike.setBikeCode(rs.getString("bikeCode"));
                bike.setBrand(rs.getString("brand"));
                bike.setPlate(rs.getString("plate"));
                bike.setRentedTime(rs.getTimestamp("rentedTime").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bike;
    }
    
    public boolean bikeCodeExists(String code) {
        String query = "SELECT 1 FROM bikes WHERE bikeCode = ?"; 
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateBike(Bike bike) {
        System.out.print(bike.getBikeCode());
        
        StringBuilder query = new StringBuilder("UPDATE bikes SET rentingTime = ?, bikeCode = ?, rentedTime = ?, dockId = ?");
        
        if(bike instanceof EBike) {
            query.append(", batteryPercentage = ?, timeRemain = ?");
        }
        
        query.append(" WHERE bike_id = ?");
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
             
            int paramIndex = 1;
            
            pstmt.setFloat(paramIndex++, bike.getRentingTime());
            pstmt.setString(paramIndex++, bike.getBikeCode());
            
            if (bike.getRentedTime() != null) {
                pstmt.setTimestamp(paramIndex++, Timestamp.valueOf(bike.getRentedTime()));
            } else {
                pstmt.setNull(paramIndex++, java.sql.Types.TIMESTAMP);
            }
            
            if (bike.getDock() != null) {
                pstmt.setInt(paramIndex++, bike.getDock().getDockId());
            } else {
                pstmt.setNull(paramIndex++, java.sql.Types.INTEGER);
            }
            
            if(bike instanceof EBike) {
                pstmt.setFloat(paramIndex++, ((EBike)bike).getBatteryPercentage());
                pstmt.setFloat(paramIndex++, ((EBike)bike).getTimeRemain());
            }
            
            pstmt.setInt(paramIndex++, bike.getBikeId());

            System.out.println(pstmt.toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.print(bike.getBikeCode());
    }

}
