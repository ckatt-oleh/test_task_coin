package app.coins;

import utils.api.models.BaseApiDataModel;

public class CoinsRequestModel extends BaseApiDataModel {
    private String amount;

    public String getAmount() {
        return amount;
    }

    public CoinsRequestModel setAmount(String amount) {
        this.amount = amount;
        return this;
    }
}
