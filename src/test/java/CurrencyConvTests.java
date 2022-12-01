import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CurrencyConvTests {

    private static Response response;

    @Test
    public void getConvRateTest(){
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.TOKEN);
        System.out.println(response.asString());
        response.then().statusCode(200);
        response.then().body("source", equalTo("USD"));
    }

    @Test
    public void getCurrencyConvRateTest() throws InterruptedException {
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.TOKEN + "&CURRENCIES=CAD,EUR,RUB");
        response.then().statusCode(200);
        response.then().body("source", equalTo("USD"));
        System.out.println(response.asString());
        Thread.sleep(3000);
    }

    @Test
    public void validateJSONResponse(){
        response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.TOKEN);
        response.then().statusCode(200);
        response.then().body("source", equalTo("USD"));
        response.then().body("success", equalTo(true));
    }



}
