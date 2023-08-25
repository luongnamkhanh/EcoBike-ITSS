package application.entity;

public class TwinBike extends Bike {

    public TwinBike() {
        super("TwinBike");
    }

    @Override
    protected void setPrice() {
        this.price = 550000.0f;
    }

    @Override
    public String getBikeType() {
        return "TwinBike";
    }

    @Override
    public double getRetingFee() {
        double fee = super.getRetingFee(); 
        fee = fee * 1.5; 
        return fee;
    }

}
