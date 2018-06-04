package app.coins;

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

import static java.lang.Integer.parseInt;

@RunWith(DataProviderRunner.class)
public class CoinsTest extends BaseApiTest {

    @BeforeClass
    public static void init() {
        BaseApiTest.init();
    }

    /** Проверка на минимальное обрабатываемое значение*/
    @DataProvider
    public static Object[][] dataProviderAddMin() {
        return new Object[][]{
                {new CoinsRequestModel().setAmount("1")},
                {new CoinsRequestModel().setAmount("0")}
        };
    }

    @Test
    @UseDataProvider("dataProviderAddMin")
    public void CorrectDataBoundaryValueMin(Object data) throws Exception {
        CoinsStep сoinsStep = new CoinsStep();
        Coins coins = сoinsStep.meWithCorrectData((CoinsRequestModel) data);
        coins.checkSuccess();

        Gson gson = new Gson();
        CoinsResponseModel ResponseModel = gson.fromJson(coins.getResponse().body().asString(), CoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(parseInt(((CoinsRequestModel) data).getAmount()));

        Assert.assertEquals(amountCalc.getPrice(),ResponseModel.getPayment().getAmount(), 0.0);
    }

    /** Проверка на максимальное обрабатываемое значение*/
    @DataProvider
    public static Object[][] dataProviderAddMax() {
        return new Object[][]{
                {new CoinsRequestModel().setAmount("2147483647")},
                {new CoinsRequestModel().setAmount("2147483646")}
        };
    }

    @Test
    @UseDataProvider("dataProviderAddMax")
    public void CorrectDataBoundaryValueMax(Object data) throws Exception {
        double expected = 0;
        CoinsStep сoinsStep = new CoinsStep();
        Coins coins = сoinsStep.meWithCorrectData((CoinsRequestModel) data);
        coins.checkSuccess();

        Gson gson = new Gson();
        CoinsResponseModel ResponseModel = gson.fromJson(coins.getResponse().body().asString(), CoinsResponseModel.class);

        AmountCalc amountCalc = new AmountCalc();
        amountCalc.countPrice(parseInt(((CoinsRequestModel) data).getAmount()));

        Assert.assertEquals(amountCalc.getPrice(),ResponseModel.getPayment().getAmount(), 0.0);
    }

    /** Проверка на минимальное обрабатываемое значение*/
    @DataProvider
    public static Object[][] incorrectdataProviderAdd() {
        return new Object[][]{
                {new CoinsRequestModel().setAmount("2147483648")},
                {new CoinsRequestModel().setAmount(null)},
                {new CoinsRequestModel().setAmount("")},
                {new CoinsRequestModel().setAmount("hgvhjabhjc")},
                {new CoinsRequestModel().setAmount("-1")},
        };
    }

    @Test
    @UseDataProvider("incorrectdataProviderAdd")
    public void IncorrectData(CoinsRequestModel data) throws Exception {
        System.out.println("incorrectData is Run: " + data.getAmount());
        CoinsStep сoinsStep = new CoinsStep();
        Coins coins = сoinsStep.meWithInCorrectData(data);
        coins.checkDenied();
        System.out.println("-----------------------------");
    }



}