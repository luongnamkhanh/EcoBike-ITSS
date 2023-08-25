package application.subsystem;

import application.entity.PaymentTransaction;

public class Interbank implements InterbankInterface {

    @Override
    public Response performTransaction(PaymentTransaction transaction, String cardCode, String owner, String dateExpired, String command, String transactionContent, double amount, String createdAt) {
        // Simulate a simple transaction logic
        boolean isSuccess = true; // assume the transaction is successful
        int errorCode = isSuccess ? SUCCESS : INSUFFICIENT_BALANCE; // Assign an error code based on success

        String transactionId = "TXN123";
        // Create a response object
        Response response = new Response(errorCode, transaction, cardCode, owner, dateExpired, transactionId, transactionContent, amount, createdAt);

        return response;
    }

    public static final int SUCCESS = 0;
    public static final int INVALID_CARD = 1;
    public static final int INSUFFICIENT_BALANCE = 2;
    public static final int INTERNAL_SERVER_ERROR = 3;
    public static final int SUSPECTED_FRAUD = 4;
    public static final int INSUFFICIENT_INFORMATION = 5;
    public static final int INVALID_AMOUNT = 7;

    // Error codes

}
