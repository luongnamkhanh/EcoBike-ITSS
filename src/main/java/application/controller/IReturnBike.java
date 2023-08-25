package application.controller;
import application.entity.*;

public interface IReturnBike {
	public float returnBike(Bike bike, Dock dock, float deposite, CreditCard crCard);
}