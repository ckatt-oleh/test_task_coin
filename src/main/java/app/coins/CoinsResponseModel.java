package app.coins;

import app.payment.Payment;
import utils.api.models.BaseApiDataModel;


public class CoinsResponseModel extends BaseApiDataModel {
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
