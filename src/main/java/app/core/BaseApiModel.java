package app.core;

import io.restassured.response.Response;
import org.junit.Assert;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseApiModel {
    private String path;
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public BaseApiModel checkSuccess() {
        Assert.assertEquals(200, this.getResponse().getStatusCode());

        return this;
    }

    public BaseApiModel checkDenied() {
        Assert.assertEquals(404, this.getResponse().getStatusCode());
//        Assert.assertEquals(this.getResponse().body().asString().length(), 0);

        return this;
    }

    public BaseApiModel checkJsonSchema(String schemaPath) {
        System.out.println("Check JSON schema: " + schemaPath);
        assertThat(this.response.body().asString(), matchesJsonSchemaInClasspath(schemaPath));

        return this;
    }
}
