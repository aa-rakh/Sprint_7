import io.restassured.response.Response;
import org.example.Courier;
import org.example.ReturnedJson;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {

    @Test
    public void createCourierPositiveResult() {

        Courier newCourier = new Courier("CourierA", "1111", "Ivan" );
        Response response = newCourier.sendRequestCreate(newCourier);
        response.then().assertThat()
                .statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    public void createCourierTwiceGetError() {

        Courier newCourier = new Courier("CourierA", "1111", "Ivan" );
        newCourier.sendRequestCreate(newCourier);
        Response response1 = newCourier.sendRequestCreate(newCourier);
        response1.then().assertThat()
                .statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void deleteCourier(){
        //получить айдишник курьера - залогиниться
        Courier newCourier = new Courier("CourierA", "1111", "Ivan" );
        Response response = newCourier.sendRequestLogin(newCourier);
        String id = response.getBody().as(ReturnedJson.class).getId();
        String url = "/api/v1/courier/" + id;
        // удалить запросом курьера
        Response response_del = newCourier.sendRequestDelete(url);
        response_del.then().assertThat()
                .statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }
}
