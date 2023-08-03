import io.restassured.response.ValidatableResponse;
import org.example.OrdersListHttpClient;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;


public class OrdersListTest {

    @Test
    public void getOrdersListNotEmpty() {
        OrdersListHttpClient orderList = new OrdersListHttpClient();
        ValidatableResponse response = orderList.getOrdersList();
        response.assertThat()
                .statusCode(200)
                .body("orders.size()", greaterThan(0));
    }

}
