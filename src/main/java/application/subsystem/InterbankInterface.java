package application.subsystem;

import application.entity.PaymentTransaction;

public interface InterbankInterface {
    public Response performTransaction(PaymentTransaction transaction, String cardCode, String owner, String dateExpired, String command, String transactionContent, double amount, String createdAt);
}
