package app.payment;

import utils.api.models.BaseApiDataModel;

public class Payment extends BaseApiDataModel {
    private double amount;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
