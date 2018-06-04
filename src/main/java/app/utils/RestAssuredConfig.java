package app.utils;

import io.restassured.RestAssured;

public class RestAssuredConfig {

    private static boolean isInit  = false;

    public static void init () {
        if (!RestAssuredConfig.isInit) {
            RestAssured.baseURI = "http://localhost:8080";
            RestAssured.useRelaxedHTTPSValidation();
            RestAssuredConfig.isInit = true;
        }

    }

}
