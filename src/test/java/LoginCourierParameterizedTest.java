import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Courier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginCourierParameterizedTest {
    private final String login;
    private final String password;
    private final Integer statusCode;
    private final String fieldNameReturned;
    private final String message;

    public LoginCourierParameterizedTest(String login, String password, Integer statusCode, String fieldNameReturned, String message) {
        this.login = login;
        this.password = password;
        this.statusCode = statusCode;
        this.fieldNameReturned = fieldNameReturned;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] testDtaGenerate() {
        return new Object[][]{
                { "", "1111", 400, "message", "Недостаточно данных для входа"},
                {"CourierB", "", 400, "message", "Недостаточно данных для входа"},
                {"CourierB", "1111", 404, "message", "Учетная запись не найдена"},
        };
    }

    @Test
    public void loginCourierIncorrectData() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        Courier newCourier = new Courier(login, password, "Ben");
        Response response = newCourier.sendRequestLogin(newCourier);
        response.then().assertThat()
                .statusCode(statusCode)
                .and()
                .body(fieldNameReturned, equalTo(message));

    }
}
