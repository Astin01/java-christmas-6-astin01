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
    void 모든_세일_혜택받았을때_각_세일금액_테스트() {
        String inputDate = "3";
        HashMap<String, String> input = new HashMap<>() {{
            put("티본스테이크", "1");
            put("바비큐립", "1");
            put("초코케이크", "2");
            put("제로콜라", "1");

        }};
        Order menu = new Order(input);
        Sales sale = new Sales(inputDate, menu);
        Map<Event, Integer> saleList = sale.getSale();

        assertAll(
                () -> assertEquals(saleList.get(Event.D_DAY), 1200),
                () -> assertEquals(saleList.get(Event.WEEKEND), 4046),
                () -> assertEquals(saleList.get(Event.SPECIAL), 1000),
                () -> assertEquals(saleList.get(Event.GIVE), 25_000)
        );
    }

    @Test
    void 모든_세일_혜택받았을때_전체_세일금액_테스트() {
        String inputDate = "3";
        HashMap<String, String> input = new HashMap<>() {{
            put("티본스테이크", "1");
            put("바비큐립", "1");
            put("초코케이크", "2");
            put("제로콜라", "1");

        }};
        Order menu = new Order(input);
        Sales sale = new Sales(inputDate, menu);

        int sum = Sum.calculateSaleSum(sale.getSale());

        assertEquals(sum, 31_246);
    }

}
