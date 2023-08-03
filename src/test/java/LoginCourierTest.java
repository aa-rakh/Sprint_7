import io.restassured.response.ValidatableResponse;
import org.example.CourierDTO;
import org.example.CourierHttpClient;
import org.example.ReturnedJsonForRequestLogin;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginCourierTest {

    private final CourierHttpClient courierHttpClient = new CourierHttpClient();

    @Test
    public void loginCourierSuccess() {
        CourierDTO request = new CourierDTO("CourierA", "1111", "Ivan");
        courierHttpClient.createCourier(request);
        ValidatableResponse response = courierHttpClient.loginCourier(request);
        ReturnedJsonForRequestLogin responseBody = response.extract().body().as(ReturnedJsonForRequestLogin.class);
        assertThat(response.extract().statusCode()).isEqualTo(200);
        assertThat(responseBody.getId()).isNotNull();
        // почистить за собой данные
        String id = responseBody.getId();
        ValidatableResponse responseDel = courierHttpClient.deleteCourier(id);
        responseDel.assertThat()
                .statusCode(200);
    }

}
