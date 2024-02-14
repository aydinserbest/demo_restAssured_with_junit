package acceptancetests;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class JSONPathAssertionExerciseTest {
    @Before
    public void prepare_rest_config() {
        RestAssured.baseURI = "http://localhost:9000/api/";
    }

    @Test
    public void find_a_simple_field_value() {
        given().pathParam("symbol", "aapl")
                .when().get("stock/{symbol}/company")
                .then().body("industry", equalTo("Telecommunications Equipment"));
        Ensure.that("the industry correctly defined",
                response-> response.body("industry", equalTo("Telecommunications Equipment")))
                .andThat("the exchange should be NASDAQ",
                        response -> response.body("exchange", equalTo("NASDAQ")));
    }
}
