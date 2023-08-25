package application.entity;

import java.util.ArrayList;
import java.util.List;
public class PaymentTransaction {
    private String content;
    private Bike bike;
    private CreditCard card;

    public PaymentTransaction(String content, Bike bike, CreditCard card) {
        this.content = content;
        this.bike = bike;
        this.card = card;
    }
    public String getContent() {
        return this.content;
    }

    public Bike getBike() {
        return this.bike;
    }

    public CreditCard getCard() {
        return this.card;
    }
}
