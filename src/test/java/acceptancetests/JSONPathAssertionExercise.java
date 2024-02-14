package acceptancetests;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class JSONPathAssertionExercise {
    @Before
    public void prepare_rest_config() {
        RestAssured.baseURI = "http://localhost:9000/api/";
    }

    @Test
    public void find_a_simple_field_value() {
        given().pathParam("symbol", "aapl")
                .when().get("stock/{symbol}/company")
                .then().body("industry", equalTo("Telecommunications Equipment"));
    }
}
