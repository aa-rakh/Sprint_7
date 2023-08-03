import io.restassured.response.ValidatableResponse;
import org.example.CourierDTO;
import org.example.CourierHttpClient;
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
    CourierHttpClient courierHttpClient = new CourierHttpClient();

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
        CourierDTO request = new CourierDTO(login, password, firstName);
        ValidatableResponse response = courierHttpClient.createCourier(request);
        response.assertThat()
                .statusCode(statusCode)
                .and()
                .body(fieldNameReturned, equalTo(message));

    }
}
