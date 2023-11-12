package christmas;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.Sales;
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
        int weekday = saleList.get(Event.WEEKDAY);
        int special = saleList.get(Event.SPECIAL);
        assertAll(
                () -> assertEquals(d_Day, 3400),
                () -> assertEquals(weekday, 0),
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

        sale.calculateSum();
        int sum = sale.getSaleSum();

        assertEquals(sum, 4400);
    }
}
