package Framwork.APIs;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;


public class APICoverage {
    private String baseURI = "http://api.walmartlabs.com/v1/";

    public JsonObject Search(String key ,String query){
        RestAssured.baseURI = baseURI + "search?";
        RequestSpecification request = RestAssured.given().param("apikey", key).param("query", query).log().all();

        Response response = (Response) request.get();
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonParser parser = new JsonParser();
        return (JsonObject)parser.parse(response.asString());
    }
}
