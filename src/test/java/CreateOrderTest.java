import io.restassured.response.ValidatableResponse;
import org.example.OrderData;
import org.example.OrderHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderData orderData;

    public CreateOrderTest(OrderData orderData){
        this.orderData = orderData;
    }
    @Parameterized.Parameters
    public static Object[][] generateTestData() {
        return new Object[][]{
                {new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha")},
                {new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha").setColor(new String[] {"BLACK"})},
                {new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha").setColor(new String[] {"GREY"})},
                {new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha").setColor(new String[] {"BLACK", "GREY"})},
        };
    }

    @Test
    public void getCreateOrderStatusCode() {
        OrderHttpClient order = new OrderHttpClient();
        System.out.println(orderData);
        ValidatableResponse response = order.createOrder(orderData);
        response.assertThat()
                .statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
