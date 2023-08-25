package application.controller;
import application.entity.*;

public class ReturnEBike implements IReturnBike{
	public float returnBike(Bike bike, Dock dock, float deposite, CreditCard crCard) {
		ICalculator calculator = new EBikeCalculator();
		float rentTime = bike.getRentingTime();
		float rentAmount = 0;

		rentAmount = calculator.rentFees(rentTime);

		float returnMoney = deposite - rentAmount;
		// calll deduce money api

		System.out.println("\nrent fee : " + rentAmount + "$");
		System.out.println("\nreturn money: " + returnMoney + " $");
		dock.addBikeToDock(bike);
		return returnMoney;
	}
}