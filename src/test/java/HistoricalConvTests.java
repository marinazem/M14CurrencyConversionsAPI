import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HistoricalConvTests {
    private static Response response;

    @Test
    public void getHistConvRateTest() throws InterruptedException {
        response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT +Consts.DATE + Consts.TOKEN);
        response.then().statusCode(200);
        response.then().body("source", equalTo("USD"));
        response.then().body("historical", equalTo(true));
        response.then().body("timestamp", equalTo(1641081599));
        response.then().body("quotes.USDCAD", equalTo(1.263915f));
        response.then().body("quotes.USDEUR", equalTo(0.879395f));
        response.then().body("quotes.USDRUB", equalTo(74.791604f));
        System.out.println(response.asString());
        Thread.sleep(3000);


    }
    @Test
    public void getErrorHistConvRateTest() throws InterruptedException {
        response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT +Consts.INVALID_DATE + Consts.TOKEN);
        response.then().statusCode(200);
        response.then().body("success", equalTo(false));

        response.then().body("error.code", equalTo(302));
        response.then().body("error.info", equalTo("You have entered an invalid date. [Required format: date=YYYY-MM-DD]"));

        System.out.println(response.asString());
        Thread.sleep(3000);


    }



}
