package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.model.Order;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @Test
    void orderSum() {
        HashMap<String, String> input = new HashMap<String, String>();
        input.put("타파스", "1");
        input.put("제로콜라", "2");
        Order menu = new Order(input);
        menu.calculateSum();

        int sum = menu.getSum();

        assertEquals(sum, 11500);

    }

}
