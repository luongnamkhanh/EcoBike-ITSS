package application.subsystem;

import application.entity.PaymentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
    private int errorCode;
    private PaymentTransaction transaction;
    private String cardCode;
    private String owner;
//    private String cvvCode;
    private String dateExpired;
    private String transactionId;
    private String transactionContent;
    private double amount;
    private String createdAt;

    public Response(int errorCode, PaymentTransaction transaction, String cardCode, String owner, String dateExpired, String transactionId, String transactionContent, double amount, String createdAt) {
        this.errorCode = errorCode;
        this.transaction = transaction;
        this.cardCode = cardCode;
        this.owner = owner;
//        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public PaymentTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(PaymentTransaction transaction) {
        this.transaction = transaction;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

//    public String getCvvCode() {
//        return cvvCode;
//    }
//
//    public void setCvvCode(String cvvCode) {
//        this.cvvCode = cvvCode;
//    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}
