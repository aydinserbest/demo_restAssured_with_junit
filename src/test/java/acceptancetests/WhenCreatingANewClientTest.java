package acceptancetests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(SerenityRunner.class)
public class WhenCreatingANewClientTest {
    @Before
    public void setupBaseUrl(){
        RestAssured.baseURI = "http://localhost:9000/api";
    }

    @Test
    public void a_new_client_can_be_created_using_a_map_structure(){
        Map<String , Object> clientData = new HashMap<>();
        clientData.put("firstName", "John");
        clientData.put("lastName", "White");
        clientData.put("email", "john@white.com");

        given().contentType(ContentType.JSON)
                .body(clientData)
                .when()
                .post("/client")
                .then()
                .statusCode(200)
                .and().body("id", not(equalTo("0")))
                .and().body("email", equalTo("john@white.com"))
                .and().body("firstName", equalTo("John"))
                .and().body("lastName", equalTo("White"));
    }
    @Test
    public void each_new_client_should_get_a_unique_id(){
        Map<String, Object> newClient = new HashMap<>();
        newClient.put("firstName", "Barbara");
        newClient.put("lastName", "Strass");
        newClient.put("email", "barbara@strass.com");

        given().contentType(ContentType.JSON)
                .body(newClient)
                .when()
                .post("/client")
                .then()
                .statusCode(200)
                .and().body("firstName",equalTo("Barbara"))
                .and().body("lastName", equalTo("Strass"))
                .and().body("id",not(equalTo("0")));
        Ensure.that("firstName should be: "+ newClient.get("firstName"),
                response -> response.body("firstName", equalTo(newClient.get("firstName"))));


    }

}
