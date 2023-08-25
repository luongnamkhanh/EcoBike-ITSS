package application.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dock {
	private int dockId;
	private String name;
	private String address;
	private List<Bike> listOfBikesBike = new ArrayList<Bike>();
	private int dockSize;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDockSize() {
		return dockSize;
	}
	public void setDockSize(int dockSize) {
		this.dockSize = dockSize;
	}
	public List<Bike> getListOfBikesBike() {
		return listOfBikesBike;
	}
	public boolean isFreeSpotAvailable() {
		return (dockSize > listOfBikesBike.size());
	}
	public boolean addBikeToDock(Bike bike) {
		if (!isFreeSpotAvailable())
			return false;
		listOfBikesBike.add(bike);
		bike.setDock(this);
		return true;
	}
	public boolean removeBikeFromDock(Bike bike) {
		return listOfBikesBike.remove(bike);
	}
	public List<Bike> getAllBikes(){
		return listOfBikesBike;
	}
	public String getDockStatus() {
		return name + "\n" + address + "\n" + listOfBikesBike.size() + "/" + dockSize;
	}
	public int getDockId() {
		return dockId;
	}
	public void setDockId(int dockId) {
		this.dockId = dockId;
	}

}
