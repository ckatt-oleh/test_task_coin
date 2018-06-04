package app.buycoins;

import app.core.BaseApiModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BuyCoins extends BaseApiModel {

    private String path = "/service/buyCoins/";

    public BuyCoins get(BuyCoinsRequestModel requestModel) {
        System.out.println("\nGET " + RestAssured.baseURI + RestAssured.basePath + this.path);

        Response response =
            given().
                queryParam("method",requestModel.getMethod()).queryParam("amount",requestModel.getAmount()).
//                contentType(ContentType.JSON).
            when().
                get(this.path).
            then().
            extract().
                response();

        this.setResponse(response);

        System.out.println("Response:");
        System.out.println("Status code: " + this.getResponse().getStatusCode());
        System.out.println("Data: " + (this.getResponse().asString().length() > 150 ? this.getResponse().asString().substring(0, 150) : this.getResponse().asString()));

        return this;
    }

    public BuyCoins checkValidJsonSchema() {
        this.checkJsonSchema("schemas/coin/buycoins-schema-valid.json");

        return this;
    }
}
