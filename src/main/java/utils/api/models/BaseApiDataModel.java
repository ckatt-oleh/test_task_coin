package utils.api.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseApiDataModel {

    public String asJsonString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(this);
    }

}
