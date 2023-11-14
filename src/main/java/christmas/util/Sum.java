package christmas.util;

import static java.lang.Integer.parseInt;

import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Sales;
import java.util.Map;

public class Sum {
    private Sum() {
    }

    public static int calculateOrderSum(Map<Menu, String> order) {
        int sum = 0;
        for (Menu i : order.keySet()) {
            int price = i.getPrice();
            int menuPrice = price * parseInt(order.get(i));
            sum += menuPrice;
        }
        return sum;
    }

    public static int calculateSaleSum(Map<Event, Integer> sale) {
        int sum = 0;
        for (Event i : sale.keySet()) {
            sum += sale.get(i);
        }
        return sum;
    }

    public static int calculateTotalOrderSum(int orderSum, int saleSum, Sales sales) {
        Map<Event, Integer> sale = sales.getSale();
        if (sale.get(Event.GIVE) != null) {
            return orderSum - saleSum + sale.get(Event.GIVE);
        }
        return orderSum - saleSum;
    }
    }
