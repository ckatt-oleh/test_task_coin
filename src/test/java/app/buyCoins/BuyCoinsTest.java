package app.buyCoins;

import app.buycoins.BuyCoins;
import app.buycoins.BuyCoinsRequestModel;
import app.buycoins.BuyCoinsResponseModel;
import app.core.BaseApiTest;
import app.math.AmountCalc;
import com.google.gson.Gson;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class BuyCoinsTest extends BaseApiTest {

    @BeforeClass
    public static void init() {
        BaseApiTest.init();
    }


    /** Проверка PAYPAL на минимальное корректное значение*/
    @Test
    public void paypalWithCorrectDataBoundaryMin() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("PAYPAL").setAmount(115);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(requestModel.getAmount());

        Assert.assertEquals(amountCalc.getPrice(),meResponseModel.getPayment().getAmount(), 0.0);
    }


    /** Проверка PAYPAL на минимальное и максимальное не обрабатываемое значение*/
    @DataProvider
    public static Object[][] dataProviderAdd() {
        return new Object[][]{
                {new BuyCoinsRequestModel().setMethod("PAYPAL").setAmount(114)},
                {new BuyCoinsRequestModel().setMethod("PAYPAL").setAmount(1)},
                {new BuyCoinsRequestModel().setMethod("PAYPAL").setAmount(0)}
        };
    }

    @Test
    @UseDataProvider("dataProviderAdd")
    public void paypalWithInCorrectDataBoundaryValue(Object data) throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData((BuyCoinsRequestModel) data);
        buyCoins.checkSuccess();
        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);
        Assert.assertEquals("Your amount is too low", meResponseModel.getPayment().getError());
    }


    /** Проверка IDEAL на минимальное корректное значение без комисии*/
    @Test
    public void idealWithCorrectDataWithoutFee() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("IDEAL").setAmount(165);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(requestModel.getAmount());

        Assert.assertEquals(amountCalc.getPrice(),meResponseModel.getPayment().getAmount(), 0.0);
    }



    /** Проверка IDEAL на минимальное корректное значение с комисияй*/
    @DataProvider
    public static Object[][] dataProviderAddBoundaryValue() {
        return new Object[][]{
                {new BuyCoinsRequestModel().setMethod("IDEAL").setAmount(1)},
                {new BuyCoinsRequestModel().setMethod("IDEAL").setAmount(2)},
        };
    }

    @Test
    @UseDataProvider("dataProviderAddBoundaryValue")
    public void idealWithCorrectDataWithFeeMIN(Object data) throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData((BuyCoinsRequestModel) data);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(((BuyCoinsRequestModel) data).getAmount());

        Assert.assertEquals((amountCalc.getPrice() + 1),meResponseModel.getPayment().getAmount(), 0.0);
    }

    /** Проверка IDEAL на максимальное корректное значение с комисияй*/
    @Test
    public void idealWithCorrectDataWithFeeMAX() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("IDEAL").setAmount(164);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(requestModel.getAmount());

        Assert.assertEquals(25.9,meResponseModel.getPayment().getAmount(), 0.0);
    }

    /** Проверка IDEAL на некоректное значение*/
    @Test
    public void idealWithIncorrectData() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("IDEAL").setAmount(0);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(requestModel.getAmount());

        Assert.assertEquals(0.0,meResponseModel.getPayment().getAmount(), 0.0);
    }



    /** Проверка CREDITCARD на минимальное корректное значение без комисии*/
    @Test
    public void creditcardWithCorrectDataWithoutFee() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("CREDITCARD").setAmount(416);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        Assert.assertEquals(50.1,meResponseModel.getPayment().getAmount(), 0.0);
    }

    /** Проверка CREDITCARD на обработку некорректного значение*/
    @Test
    public void creditcardWithIncorrectData() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("CREDITCARD").setAmount(0);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        Assert.assertEquals(0.0,meResponseModel.getPayment().getAmount(), 0.0);
    }


    /** Проверка CREDITCARD на минимальное корректное значение c комисияй 2% */
    @Test
    public void creditcardWithCorrectDataWithMaxFeeBoundaryValueMin() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("CREDITCARD").setAmount(1);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        Assert.assertEquals(0.51,meResponseModel.getPayment().getAmount(), 0.0);
    }

    /** Проверка CREDITCARD на максимальное корректное значение c комисияй 2% */
    @Test
    public void creditcardWithCorrectDataWithMaxFeeBoundaryValueMax() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("CREDITCARD").setAmount(165);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        Assert.assertEquals(25.5,meResponseModel.getPayment().getAmount(), 0.0);
    }


    /** Проверка CREDITCARD на минимальное корректное значение c комисияй 1% */
    @Test
    public void creditcardWithCorrectDataWithMinFeeBoundaryValueMin() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("CREDITCARD").setAmount(166);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        Assert.assertEquals(25.351,meResponseModel.getPayment().getAmount(), 0.0);
    }


    /** Проверка CREDITCARD на максимальное корректное значение c комисияй 1% */
    @Test
    public void creditcardWithCorrectDataWithMinFeeBoundaryValueMax() throws Exception {
        BuyCoinsStep buyCoinsStep = new BuyCoinsStep();
        BuyCoinsRequestModel requestModel = new BuyCoinsRequestModel();
        requestModel.setMethod("CREDITCARD").setAmount(415);
        BuyCoins buyCoins = buyCoinsStep.meWithCorrectData(requestModel);
        buyCoins.checkSuccess();


        Gson gson = new Gson();
        BuyCoinsResponseModel meResponseModel = gson.fromJson(buyCoins.getResponse().body().asString(),
                BuyCoinsResponseModel.class);

        Assert.assertEquals(50.5,meResponseModel.getPayment().getAmount(), 0.0);
    }



}