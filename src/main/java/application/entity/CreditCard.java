package application.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCard {
    private String cardHolderName;
    private String cardNumber;
    private String securityCode;
    private String expirationDate;
    private String issuingBank;


    public CreditCard(String cardHolderName, String cardNumber, String securityCode, String expirationDate, String issuingBank) throws IllegalArgumentException {

        //khởi tạo thuộc tính cho CreditCard
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationDate= expirationDate;
        this.issuingBank = issuingBank;
    }





    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getIssuingBank() {
        return issuingBank;
    }
}
