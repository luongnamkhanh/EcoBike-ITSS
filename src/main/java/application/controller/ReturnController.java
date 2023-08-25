package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.dao.DockDAO;
import application.entity.Bike;
import application.entity.CreditCard;
import application.entity.Dock;

public class ReturnController{
	private IReturnBike returner;
	private Bike bike;
	
	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public ReturnController(IReturnBike returner, Bike bike) {
		this.returner = returner;
		this.bike = bike;
	}

	public double proceedReturnBike(Dock dock) {
//		System.out.println("proceed return bike");
		BikeController bControl = new BikeController();
		bike.setDock(dock);
		bControl.returnBike(this.bike);
		
//		System.out.println("rented: " + bike.getRentedTime() + " renting: " + bike.getRentingTime());
		float bikeDeposite = this.bike.getDeposit();
	
		return (double)returner.returnBike(bike, dock, bikeDeposite, null);
	}
	
	private DockDAO dockDAO = new DockDAO();
	private List<Dock> listOfDocks = new ArrayList<Dock>();
	
    public List<Dock> getAllDock() {
        return dockDAO.getAllDocks();
    }
	
	public void addDockToList(Dock dock) {
		listOfDocks.add(dock);
	}
	
	public List<Dock> searchDocks(String queryString){
		List<Dock> resultDocks = new ArrayList<Dock>();
		for (int i = 0; i < listOfDocks.size(); i++) {
			if (listOfDocks.get(i).getName().contains(queryString)) {
				System.out.println("Found\n");
				resultDocks.add(listOfDocks.get(i));
			}
		}
		return resultDocks;
	}
}
