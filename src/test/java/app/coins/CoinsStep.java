package app.coins;
import app.coins.Coins;
import app.coins.CoinsResponseModel;
import com.google.gson.Gson;
import io.qameta.allure.Step;

public class CoinsStep {

    @Step
    public Coins meWithCorrectData(CoinsRequestModel requestModel) throws Exception {
        CoinsResponseModel responseModel = new CoinsResponseModel();

        Coins coins = new Coins();
        coins.get(requestModel);
        coins.checkValidJsonSchema();

        return coins;
    }

    @Step
    public Coins meWithInCorrectData(CoinsRequestModel requestModel) throws Exception {
        CoinsResponseModel responseModel = new CoinsResponseModel();

        Coins coins = new Coins();
        coins.get(requestModel);

        return coins;
    }

}