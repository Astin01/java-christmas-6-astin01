package christmas;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.model.Event;
import christmas.model.Order;
import christmas.model.Sales;
import christmas.util.Sum;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class SalesTest {
    @Test
    void saleListTest() {
        String inputDate = "25";
        HashMap<String, String> input = new HashMap<String, String>();
        input.put("타파스", "1");
        input.put("제로콜라", "2");
        Order menu = new Order(input);

        Sales sale = new Sales(inputDate, menu);
        Map<Event, Integer> saleList = sale.getSale();


        int d_Day = saleList.get(Event.D_DAY);
        int special = saleList.get(Event.SPECIAL);
        assertAll(
                () -> assertEquals(d_Day, 3400),
                () -> assertEquals(special, 1000)
        );
    }

    @Test
    void totalSaleTest() {
        String inputDate = "25";
        HashMap<String, String> input = new HashMap<String, String>();
        input.put("타파스", "1");
        input.put("제로콜라", "2");
        Order menu = new Order(input);
        Sales sale = new Sales(inputDate, menu);

        int sum = Sum.calculateSaleSum(sale.getSale());

        assertEquals(sum, 4400);
    }
}
