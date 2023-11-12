package christmas.model;

import static java.lang.Integer.parseInt;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {
    private final Map<Menu, String> order = new EnumMap<>(Menu.class);
    private int orderSum;

    public Order(HashMap<String, String> input) {
        for (String i : input.keySet()) {
            putMenu(i, input.get(i));
        }
    }

    public void calculateSum() {
        int sum = 0;
        for (Menu i : order.keySet()) {
            int price = i.getPrice();
            int menuPrice = price * parseInt(order.get(i));
            sum += menuPrice;
        }
        this.orderSum = sum;
    }

    public int getSum() {
        return this.orderSum;
    }

    private void putMenu(String menu, String count) {
        if (isMainDish(menu, count)) {
            return;
        }
        if (isAppetizer(menu, count)) {
            return;
        }
        if (isDrink(menu, count)) {
            return;
        }
        isDessert(menu, count);
    }

    public Map<Menu, String> getOrder() {
        return this.order;
    }

    private Boolean isDessert(String menu, String count) {
        if (Objects.equals(menu, "초코케이크")) {
            order.put(Menu.CHOCOLATE_CAKE, count);
            return true;
        }
        if (Objects.equals(menu, "아이스크림")) {
            order.put(Menu.CHOCOLATE_CAKE, count);
            return true;
        }
        return false;
    }

    private Boolean isDrink(String menu, String count) {
        if (Objects.equals(menu, "제로콜라")) {
            order.put(Menu.ZERO_COLA, count);
            return true;
        }
        if (Objects.equals(menu, "레드와인")) {
            order.put(Menu.RED_WINE, count);
            return true;
        }
        if (Objects.equals(menu, "샴페인")) {
            order.put(Menu.CHAMPAGNE, count);
            return true;
        }
        return false;
    }

    private Boolean isAppetizer(String menu, String count) {
        if (Objects.equals(menu, "양송이수프")) {
            order.put(Menu.MUSHROOM_SOUP, count);
            return true;
        }
        if (Objects.equals(menu, "타파스")) {
            order.put(Menu.TAPAS, count);
            return true;
        }
        if (Objects.equals(menu, "시저샐러드")) {
            order.put(Menu.CAESAR_SALAD, count);
            return true;
        }
        return false;
    }

    private Boolean isMainDish(String menu, String count) {
        if (Objects.equals(menu, "티본스테이크")) {
            order.put(Menu.T_BONE_STEAK, count);
            return true;
        }
        if (Objects.equals(menu, "바비큐립")) {
            order.put(Menu.BARBECUE_LIB, count);
            return true;
        }
        if (Objects.equals(menu, "해산물파스타")) {
            order.put(Menu.SEAFOOD_PASTA, count);
            return true;
        }
        if (Objects.equals(menu, "크리스마스파스타")) {
            order.put(Menu.CHRISTMAS_PASTA, count);
            return true;
        }
        return false;
    }
}
