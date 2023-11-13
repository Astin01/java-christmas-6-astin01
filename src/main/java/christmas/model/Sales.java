package christmas.model;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Sales {
    Map<Event, Integer> sale = new EnumMap<>(Event.class);
    private int saleSum;

    public Sales(String input, Order order) {
        int convertInput = parseInt(input);
        calculateSales(convertInput, order);
    }

    private void calculateSales(int input, Order order) {
        Map<Menu, String> orderList = order.getOrder();
        List<Integer> count = countOrder(orderList);
        int dessertCount = count.get(0);
        int mainCount = count.get(1);
        isD_Day(input);
        isWeekDay(input, dessertCount);
        isWeekend(input, mainCount);
        isSpecial(input);
        isGive(order);
    }

    public Map<Event, Integer> getSale() {
        return this.sale;
    }

    public void calculateSum() {
        int sum = 0;
        for (Event i : sale.keySet()) {
            sum += sale.get(i);
        }
        this.saleSum = sum;
    }

    public int getSaleSum() {
        return this.saleSum;
    }


    private List<Integer> countOrder(Map<Menu, String> order) {
        int dessertCount = 0;
        int mainCount = 0;
        for (Menu i : order.keySet()) {
            dessertCount += countDessert(i);
            mainCount += countMainDish(i);
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(dessertCount, mainCount));
        return list;
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

    private void isD_Day(int input) {
        if (input >= 26) {
            return;
        }
        int benefit = Event.D_DAY.getBenefit();
        benefit += (input - 1) * 100;
        sale.put(Event.D_DAY, benefit);
    }

    private void isWeekDay(int input, int dessertCount) {
        if (input % 7 != 2 && input % 7 != 3) {
            int benefit = Event.WEEKDAY.getBenefit();
            benefit *= dessertCount;
            if(dessertCount>0){
                sale.put(Event.WEEKDAY, benefit);
            }
        }
    }

    private void isWeekend(int input, int mainCount) {
        if (input % 7 == 2 || input % 7 == 3) {
            int benefit = Event.WEEKEND.getBenefit();
            benefit *= mainCount;
            if(mainCount>0){
                sale.put(Event.WEEKEND, benefit);
            }
        }
    }

    private void isSpecial(int input) {
        if (input % 7 == 3 || input == 25) {
            int benefit = Event.SPECIAL.getBenefit();
            sale.put(Event.SPECIAL, benefit);
        }
    }

    private void isGive(Order order) {
        int sum = order.getSum();
        if (sum > 120_000) {
            int price = sum/120_000;
            sale.put(Event.GIVE, price*25_000);
        }
    }
}
