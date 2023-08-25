package application.entity;

public class EBike extends Bike {

    private float batteryPercentage;
    private float timeRemain;
    private static final float MAX_TIME = 120;

    public EBike() {
        super("EBike");
        this.batteryPercentage = 100;
        this.timeRemain = MAX_TIME;
    }

    @Override
    protected void setPrice() {
        this.price = 700000.0f;
    }

    public float getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(float batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public float getTimeRemain() {
        return timeRemain;
    }

    public void setTimeRemain(float timeRemain) {
        this.timeRemain = timeRemain;
    }

    @Override
    public void setRentingTime(float rentingTime) {
        super.setRentingTime(rentingTime);
        this.timeRemain = MAX_TIME - rentingTime;
        this.batteryPercentage = (this.timeRemain / MAX_TIME) * 100;
    }

    @Override
    public String getBikeStatus() {
        return super.getBikeStatus() + "\nBattery: " + batteryPercentage + "%";
    }
    
    @Override
    public String getBikeType() {
        return "EBike";
    }
    
    @Override
    public double getRetingFee() {
        double fee = super.getRetingFee(); 
        fee = fee * 1.5;
        return fee;
    }
    
    @Override
    public String getAdditionalInfo() {
        return "Battery Percentage: " + getBatteryPercentage() + "%" + 
               "\nEstimated Time Remain: " + getTimeRemain() + " minutes";
    }

}
