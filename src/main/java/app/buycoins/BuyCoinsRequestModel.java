package app.buycoins;

import utils.api.models.BaseApiDataModel;

public class BuyCoinsRequestModel extends BaseApiDataModel {
    private String method;
    private int amount;

    public String getMethod() {
        return method;
    }

    public BuyCoinsRequestModel setMethod(String method) {
        this.method = method;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public BuyCoinsRequestModel setAmount(int amount) {
        this.amount = amount;
        return this;
    }
}
