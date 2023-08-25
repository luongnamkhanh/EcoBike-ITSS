package application.entity;

import java.time.LocalDateTime;

public class Bike {
	protected int bikeId;
	protected String bikeType;
	protected String plate;
	protected float price;
	protected float rentingTime;
	protected Dock dock;
	protected String bikeCode;
	protected String brand;
	protected LocalDateTime rentedTime;
	protected boolean status;
	
	
	public Bike(String bikeType) {
		this.bikeType = bikeType;
		this.rentingTime = 0;
		this.status=true;
		setPrice();
	}
	
	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	
    public String getBikeType() {
        return "StandardBike";
    }

	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
	
    public String getPlate() {
        return plate;
    }

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
    protected void setPrice() {
        this.price = 400000.0f;
    }
	
	public float getPrice() {
		return price;
	}
	
	public float getDeposit() {
		return price * (float)0.4;
	}
	
	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}
	
	public void removeFromDock() {
	    this.dock = null;
	}

	public float getRentingTime() {
		return rentingTime;
	}

	public void setRentingTime(float rentingTime) {
		this.rentingTime = rentingTime;
	}

	public String getBikeStatus() {
		return bikeType.toString();
	}

	public String getBikeCode() {
		return bikeCode;
	}

	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public LocalDateTime getRentedTime() {
	    return rentedTime;
	}

	public void setRentedTime(LocalDateTime rentedTime) {
	    this.rentedTime = rentedTime;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public double getRetingFee() {
		double fee = 10000;
		if (rentingTime > 30)
			fee = Math.ceil((rentingTime - 30)/15) * 3000;
		
		return fee;
	}
	
    public String getAdditionalInfo() {
        return ""; // By default, no additional info for a generic Bike.
    }
}
