import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Courier;
import org.example.ReturnedJson;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void loginCourierSuccess() {
        Courier newCourier = new Courier("CourierA", "1111", "Ivan" );
        newCourier.sendRequestCreate(newCourier);
        Response response = newCourier.sendRequestLogin(newCourier);
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
        // почистить за собой данные
        String id = response.getBody().as(ReturnedJson.class).getId();
        String url = "/api/v1/courier/" + id;
        newCourier.sendRequestDelete(url)
                .then().assertThat()
                .statusCode(200);
    }

}
