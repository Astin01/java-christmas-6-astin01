package christmas.model;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Order {
    private final Map<Menu, String> order = new EnumMap<>(Menu.class);

    public Order(HashMap<String, String> input) {
        for (String i : input.keySet()) {
            putMenu(i, input.get(i));
        }
    }

    private void putMenu(String menu, String count) {
        Arrays.stream(MenuGroup.values())
                .forEach(menuGroup -> sortMenu(menu, count, menuGroup.getMenuList()));
    }

    private void sortMenu(String menu, String count, List<Menu> menuList) {
        for (Menu targetMenu : menuList) {
            if (targetMenu.getName().equals(menu)) {
                order.put(targetMenu, count);
            }
        }
    }

    public Map<Menu, String> getOrder() {
        return this.order;
    }


}
