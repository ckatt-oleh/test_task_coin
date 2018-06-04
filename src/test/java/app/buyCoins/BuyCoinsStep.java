package app.buyCoins;
import app.buycoins.BuyCoins;
import app.buycoins.BuyCoinsRequestModel;
import app.buycoins.BuyCoinsResponseModel;
import com.google.gson.Gson;
import io.qameta.allure.Step;

public class BuyCoinsStep {

    @Step
    public BuyCoins meWithCorrectData(BuyCoinsRequestModel requestModel) throws Exception {
        BuyCoinsResponseModel responseModel = new BuyCoinsResponseModel();

        BuyCoins buyCoins = new BuyCoins();
        buyCoins.get(requestModel);
        System.out.println(responseModel.asJsonString());
        buyCoins.checkValidJsonSchema();

        return buyCoins;
    }

}