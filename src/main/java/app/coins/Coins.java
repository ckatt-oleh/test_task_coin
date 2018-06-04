package app.coins;

import app.core.BaseApiModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Coins extends BaseApiModel {

    private String path = "/service/coins/";

    public Coins get(CoinsRequestModel requestModel) {
        System.out.println("\nGET " + RestAssured.baseURI + RestAssured.basePath + this.path);

        Response response =
                given().
                  queryParam("amount",requestModel.getAmount()).
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

    public Coins checkValidJsonSchema() {
        this.checkJsonSchema("schemas/coin/coins-schema-valid.json");

        return this;
    }
}
