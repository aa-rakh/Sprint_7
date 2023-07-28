import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final String fileName;

    public CreateOrderTest(String fileName) {
        this.fileName = fileName;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters
    public static Object[][] getFileName() {
        return new Object[][]{
                {"NewOrderData_BLACK"},
                {"NewOrderData_GREY"},
                {"NewOrderData_BLACK_and_GREY"},
                {"NewOrderData_without_color"},
        };
    }

    @Test
    public void getCreateOrderStatusCode() {
        File json = new File("./src/main/resources/" + fileName + ".json");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/orders");
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
