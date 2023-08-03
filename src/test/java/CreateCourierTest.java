import io.restassured.response.ValidatableResponse;
import org.example.CourierDTO;
import org.example.CourierHttpClient;
import org.example.ReturnedJsonForRequestLogin;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    private final CourierHttpClient courierHttpClient = new CourierHttpClient();
    CourierDTO request = new CourierDTO("CourierA", "1111", "Ivan" );

    @Test
    public void createCourierPositiveResult() {
        ValidatableResponse response = courierHttpClient.createCourier(request);
        response.assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    public void createCourierTwiceGetError() {
        courierHttpClient.createCourier(request);
        ValidatableResponse responseCreateCourier = courierHttpClient.createCourier(request);
        responseCreateCourier.assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void deleteCourier(){
        //получить айдишник курьера - залогиниться
        ValidatableResponse response = courierHttpClient.loginCourier(request);
        ReturnedJsonForRequestLogin responseBody = response.extract().body().as(ReturnedJsonForRequestLogin.class);
        String id = responseBody.getId();
        ValidatableResponse responseDel = courierHttpClient.deleteCourier(id);
        responseDel.assertThat()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }
}
