package app.core;

import app.utils.RestAssuredConfig;

public class BaseApiTest {
    public static void init() {
        RestAssuredConfig.init();
    }
}
