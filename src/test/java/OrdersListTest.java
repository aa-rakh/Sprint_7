import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest {

    @Test
    public void getOrdersList() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Response response = given()
                .get("/api/v1/orders");
        response.then().assertThat()
                .statusCode(200)
                .and()
                .body("orders", notNullValue());
    }

}
