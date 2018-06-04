package app.buycoins;

import app.payment.Payment;
import utils.api.models.BaseApiDataModel;

import java.util.List;


public class BuyCoinsResponseModel extends BaseApiDataModel {
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
