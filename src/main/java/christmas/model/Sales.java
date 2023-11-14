package christmas.model;

import static java.lang.Integer.parseInt;

import christmas.util.Sum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Sales {
    private static final int SALE_CRITERIA = 10_000;
    private static final int D_DAY_CRITERIA = 26;
    private static final int D_DAY_BENEFIT = 100;
    private static final int CHRISTMAS = 25;
    private static final int GIFT_CRITERIA = 12_000;

    private final Map<Event, Integer> sale = new EnumMap<>(Event.class);

    public Sales(String input, Order order) {
        int convertInput = parseInt(input);
        int orderSum = Sum.calculateOrderSum(order.getOrder());
        if (orderSum >= SALE_CRITERIA) {
            calculateSales(convertInput, order);
        }
    }

    private void calculateSales(int input, Order order) {
        Map<Menu, String> orderList = order.getOrder();
        List<Integer> count = countOrder(orderList);
        int dessertCount = count.get(0);
        int mainCount = count.get(1);

        judgeSales(input,mainCount,dessertCount,order);
    }

    private List<Integer> countOrder(Map<Menu, String> order) {
        int dessertCount = 0;
        int mainCount = 0;
        for (Menu i : order.keySet()) {
            dessertCount += countDessert(i);
            mainCount += countMainDish(i);
        }
        return new ArrayList<>(Arrays.asList(dessertCount, mainCount));
    }

    private int countDessert(Menu i) {
        MenuGroup menu = MenuGroup.findByMenu(i);
        if (menu.getMenuTitle().equals("디저트")) {
            return 1;
        }
        return 0;
    }

    private int countMainDish(Menu i) {
        MenuGroup menu = MenuGroup.findByMenu(i);
        if (menu.getMenuTitle().equals("메인메뉴")) {
            return 1;
        }
        return 0;
    }

    private void judgeSales(int input, int mainCount, int dessertCount,Order order){
        isD_Day(input);
        isWeekDay(input, dessertCount);
        isWeekend(input, mainCount);
        isSpecial(input);
        isGive(order);
    }

    private void isD_Day(int day) {
        if (day <= D_DAY_CRITERIA) {
            int benefit = Event.D_DAY.getBenefit();
            benefit += (day - 1) * D_DAY_BENEFIT;
            sale.put(Event.D_DAY, benefit);
        }
    }

    private void isWeekDay(int input, int dessertCount) {
        if (input % 7 != 2 && input % 7 != 3) {
            int benefit = Event.WEEKDAY.getBenefit();
            benefit *= dessertCount;
            if (dessertCount > 0) {
                sale.put(Event.WEEKDAY, benefit);
            }
        }
    }

    private void isWeekend(int input, int mainCount) {
        if (input % 7 == 2 || input % 7 == 3) {
            int benefit = Event.WEEKEND.getBenefit();
            benefit *= mainCount;
            if (mainCount > 0) {
                sale.put(Event.WEEKEND, benefit);
            }
        }
    }

    private void isSpecial(int input) {
        if (input % 7 == 3 || input == CHRISTMAS) {
            int benefit = Event.SPECIAL.getBenefit();
            sale.put(Event.SPECIAL, benefit);
        }
    }

    private void isGive(Order order) {
        int orderSum = Sum.calculateOrderSum(order.getOrder());
        int giftPrice = Event.GIVE.getBenefit();
        if (orderSum > GIFT_CRITERIA) {
            sale.put(Event.GIVE, giftPrice);
        }
    }

    public Map<Event, Integer> getSale() {
        return this.sale;
    }

}
