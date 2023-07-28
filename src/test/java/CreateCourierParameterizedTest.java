import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Courier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CreateCourierParameterizedTest {
    private final String login;
    private final String password;
    private final String firstName;

    private final Integer statusCode;
    private final String fieldNameReturned;
    private final String message;

    public CreateCourierParameterizedTest(String login, String password, String firstName, Integer statusCode, String fieldNameReturned, String message) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.statusCode = statusCode;
        this.fieldNameReturned = fieldNameReturned;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] testDtaGenerate() {
        return new Object[][]{
                {"", "1111", "Ivan", 400, "message", "Недостаточно данных для создания учетной записи"},
                {"CourierB", "", "Ivan", 400, "message", "Недостаточно данных для создания учетной записи"},
        };
    }

    @Test
    public void createCourierIncorrectData() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Courier newCourier = new Courier(login, password, firstName);
        Response response = newCourier.sendRequestCreate(newCourier);
        response.then().assertThat()
                .statusCode(statusCode)
                .and()
                .body(fieldNameReturned, equalTo(message));

    }
}
